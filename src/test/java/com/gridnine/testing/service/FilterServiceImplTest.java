package com.gridnine.testing.service;

import com.gridnine.testing.flightBuilder.FlightBuilder;
import com.gridnine.testing.models.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterServiceImplTest {
    FilterService filterService = new FilterServiceImpl();
    static List<Flight> flights;

    @BeforeAll
    public static void createList() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    public void removeFlightsBeforeNowTest() {
        List<Flight> futureFlights = new ArrayList<>(flights);
        futureFlights.remove(2);
        Assertions.assertEquals(futureFlights,
                filterService.removeFlightsBeforeLocalDateTime(flights, LocalDateTime.now()));
    }

    @Test
    public void removeFlightsBefore2050Test() {
        List<Flight> future2050Flights = new ArrayList<>();
        Assertions.assertEquals(future2050Flights,
                filterService.removeFlightsBeforeLocalDateTime(flights, LocalDateTime.now().plusYears(26)));
    }

    @Test
    public void removeFlightsBefore2000Test() {
        Assertions.assertEquals(flights,
                filterService.removeFlightsBeforeLocalDateTime(flights, LocalDateTime.now().minusYears(24)));
    }

    @Test
    public void removeFlightsWithArrivalBeforeDepartureTest() {
        List<Flight> correctFlights = new ArrayList<>(flights);
        correctFlights.remove(3);
        Assertions.assertEquals(correctFlights, filterService.removeFlightsWithArrivalBeforeDeparture(flights));
    }

    @Test
    public void removeLongConnectionsTest() {
        List<Flight> flightsWithShortConnections = new ArrayList<>(flights);
        flightsWithShortConnections.remove(5);
        flightsWithShortConnections.remove(4);

        Assertions.assertEquals(flightsWithShortConnections,
                filterService.removeLongConnections(flights, Duration.ofHours(2)));
    }

    @Test
    public void remove10HoursConnectionsTest() {
        Assertions.assertEquals(flights,
                filterService.removeLongConnections(flights, Duration.ofHours(10)));
    }

    @Test
    public void removeZeroMinutesConnectionsTest() {
        List<Flight> flightsWithoutConnections = new ArrayList<>(flights);
        flightsWithoutConnections.remove(5);
        flightsWithoutConnections.remove(4);
        flightsWithoutConnections.remove(1);
        Assertions.assertEquals(flightsWithoutConnections,
                filterService.removeLongConnections(flights, Duration.ofMinutes(0)));
    }

    @Test
    public void filterByDepartureDayTest() {
        LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
        List<Flight> threeDaysFromNowFlights = new ArrayList<>(flights);
        threeDaysFromNowFlights.remove(2);
        Assertions.assertEquals(threeDaysFromNowFlights, filterService.filterByDepartureDay(flights, threeDaysFromNow));
    }

    @Test
    public void filterByDepartureDayNoFlightsTest() {
        LocalDate tenDaysFromNow = LocalDate.now().plusDays(10);
        List<Flight> emptyList = new ArrayList<>();
        Assertions.assertEquals(emptyList, filterService.filterByDepartureDay(flights, tenDaysFromNow));
    }

    @Test
    public void getFlightsWithMax1AmountOfConnectionsTest() {
        List<Flight> flightsWithMax1Connection = new ArrayList<>(flights);
        flightsWithMax1Connection.remove(5);
        Assertions.assertEquals(flightsWithMax1Connection,
                filterService.getFlightsWithMaxAmountOfConnections(flights, 1));
    }

    @Test
    public void getFlightsWithoutConnectionsTest() {
        List<Flight> flightsWithoutConnections = new ArrayList<>(flights);
        flightsWithoutConnections.remove(5);
        flightsWithoutConnections.remove(4);
        flightsWithoutConnections.remove(1);
        Assertions.assertEquals(flightsWithoutConnections,
                filterService.getFlightsWithMaxAmountOfConnections(flights, 0));
    }
}