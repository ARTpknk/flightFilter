package com.gridnine.testing.controller;

import com.gridnine.testing.exceptions.NegativeAmountOfConnections;
import com.gridnine.testing.exceptions.NegativeDurationException;
import com.gridnine.testing.flightBuilder.FlightBuilder;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.FlightFilterSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterControllerImplTest {
    FilterController filterController = new FilterControllerImpl();
    static List<Flight> flights;

    @BeforeAll
    public static void createList() {
        flights = FlightBuilder.createFlights();

    }

    @Test
    public void zeroFiltersTest() {
        FlightFilterSpecification zeroFilters = new FlightFilterSpecification(null,
                false,
                null,
                null,
                null);
        Assertions.assertEquals(flights,
                filterController.filterFlights(flights, zeroFilters));
    }

    @Test
    public void multipleFiltersTest() {
        FlightFilterSpecification multipleFilters = new FlightFilterSpecification(LocalDateTime.now(),
                true,
                Duration.ofHours(2),
                LocalDate.now().plusDays(3),
                2);
        List<Flight> rightFlights = new ArrayList<>();
        rightFlights.add(flights.get(0));
        rightFlights.add(flights.get(1));

        Assertions.assertEquals(rightFlights,
                filterController.filterFlights(flights, multipleFilters));
    }

    @Test
    public void theEarliestTimeSpotForDepartureFilterTest() {
        FlightFilterSpecification theEarliestTimeSpotForDepartureFilter =
                new FlightFilterSpecification(LocalDateTime.now(),
                        false,
                        null,
                        null,
                        null);
        List<Flight> futureFlights = new ArrayList<>(flights);
        futureFlights.remove(2);

        Assertions.assertEquals(futureFlights,
                filterController.filterFlights(flights, theEarliestTimeSpotForDepartureFilter));
    }

    @Test
    public void areFlightsWithArrivalBeforeDepartureRemovingFilterTest() {
        FlightFilterSpecification areFlightsWithArrivalBeforeDepartureRemovingFilter =
                new FlightFilterSpecification(null,
                        true,
                        null,
                        null,
                        null);
        List<Flight> correctFlights = new ArrayList<>(flights);
        correctFlights.remove(3);

        Assertions.assertEquals(correctFlights,
                filterController.filterFlights(flights, areFlightsWithArrivalBeforeDepartureRemovingFilter));
    }

    @Test
    public void maxConnectionTimeFilterTest() {
        FlightFilterSpecification maxConnectionTimeFilter =
                new FlightFilterSpecification(null,
                        false,
                        Duration.ofHours(2),
                        null,
                        null);
        List<Flight> flightsWithShortConnections = new ArrayList<>(flights);
        flightsWithShortConnections.remove(5);
        flightsWithShortConnections.remove(4);

        Assertions.assertEquals(flightsWithShortConnections,
                filterController.filterFlights(flights, maxConnectionTimeFilter));
    }

    @Test
    public void departureDateFilterTest() {
        FlightFilterSpecification departureDateFilter = new FlightFilterSpecification(null,
                false,
                null,
                LocalDate.now().plusDays(3),
                null);
        List<Flight> threeDaysFromNowFlights = new ArrayList<>(flights);
        threeDaysFromNowFlights.remove(2);

        Assertions.assertEquals(threeDaysFromNowFlights,
                filterController.filterFlights(flights, departureDateFilter));
    }

    @Test
    public void maxAmountOfConnectionsFilterTest() {
        FlightFilterSpecification maxAmountOfConnectionsFilter =
                new FlightFilterSpecification(null,
                        false,
                        null,
                        null,
                        1);
        List<Flight> flightsWithMax1Connection = new ArrayList<>(flights);
        flightsWithMax1Connection.remove(5);

        Assertions.assertEquals(flightsWithMax1Connection,
                filterController.filterFlights(flights, maxAmountOfConnectionsFilter));
    }

    @Test
    public void NegativeMaxConnectionTimeTest() {
        FlightFilterSpecification errorFilter =
                new FlightFilterSpecification(null,
                        false,
                        Duration.ofHours(-2),
                        null,
                        null);

        Assertions.assertThrows(NegativeDurationException.class,
                () -> filterController.filterFlights(flights, errorFilter));
    }

    @Test
    public void NegativeMaxAmountOfConnectionsTest() {
        FlightFilterSpecification errorFilter =
                new FlightFilterSpecification(null,
                        false,
                        null,
                        null,
                        -1);

        Assertions.assertThrows(NegativeAmountOfConnections.class,
                () -> filterController.filterFlights(flights, errorFilter));
    }
}