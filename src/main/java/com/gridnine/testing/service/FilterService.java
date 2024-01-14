package com.gridnine.testing.service;

import com.gridnine.testing.models.Flight;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FilterService {
    List<Flight> removeFlightsBeforeLocalDateTime(List<Flight> flights, LocalDateTime time);

    List<Flight> removeFlightsWithArrivalBeforeDeparture(List<Flight> flights);

    boolean isArrivalAfterDeparture(Flight flight);

    List<Flight> removeLongConnections(List<Flight> flights, Duration maxConnectionTime);

    Duration getConnectionsDuration(Flight flight);

    //Get the flights with a departure on a selected day
    List<Flight> filterByDepartureDay(List<Flight> flights, LocalDate date);

    List<Flight> getFlightsWithMaxAmountOfConnections(List<Flight> flights, Integer maxAmountOfConnections);
}