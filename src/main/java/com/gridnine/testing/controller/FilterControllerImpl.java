package com.gridnine.testing.controller;

import com.gridnine.testing.exceptions.NegativeAmountOfConnections;
import com.gridnine.testing.exceptions.NegativeDurationException;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.FlightFilterSpecification;
import com.gridnine.testing.service.FilterService;
import com.gridnine.testing.service.FilterServiceImpl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FilterControllerImpl implements FilterController {
    FilterService filterService = new FilterServiceImpl();

    @Override
    public List<Flight> filterFlights(List<Flight> flights, FlightFilterSpecification flightFilter) {
        LocalDateTime theEarliestTimeSpotForDeparture = flightFilter.getTheEarliestTimeSpotForDeparture();
        boolean areFlightsWithArrivalBeforeDepartureRemoving =
                flightFilter.isAreFlightsWithArrivalBeforeDepartureRemoving();
        Duration maxConnectionTime = flightFilter.getMaxConnectionTime();
        LocalDate departureDate = flightFilter.getDepartureDate();
        Integer maxAmountOfConnections = flightFilter.getMaxAmountOfConnections();

        if (theEarliestTimeSpotForDeparture != null) {
            flights = filterService.removeFlightsBeforeLocalDateTime(flights, theEarliestTimeSpotForDeparture);
        }
        if (areFlightsWithArrivalBeforeDepartureRemoving) {
            flights = filterService.removeFlightsWithArrivalBeforeDeparture(flights);
        }
        if (maxConnectionTime != null) {
            if (maxConnectionTime.isNegative()) {
                throw new NegativeDurationException("maxConnectionTime should not be negative");
            }
            flights = filterService.removeLongConnections(flights, maxConnectionTime);
        }
        if (departureDate != null) {
            flights = filterService.filterByDepartureDay(flights, departureDate);
        }
        if (maxAmountOfConnections != null) {
            if (maxAmountOfConnections < 0) {
                throw new NegativeAmountOfConnections("Negative amount of connections. Min is 0. Your amount is "
                        + maxAmountOfConnections);
            }
            flights = filterService.getFlightsWithMaxAmountOfConnections(flights, maxAmountOfConnections);
        }
        return flights;
    }
}