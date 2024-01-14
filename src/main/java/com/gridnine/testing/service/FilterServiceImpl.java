package com.gridnine.testing.service;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FilterServiceImpl implements FilterService {
    @Override
    public List<Flight> removeFlightsBeforeLocalDateTime(List<Flight> flights, LocalDateTime time) {
        return flights.stream()
                .filter((Flight flight) -> flight.getSegments().get(0).getDepartureDate().isAfter(time))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> removeFlightsWithArrivalBeforeDeparture(List<Flight> flights) {
        return flights.stream().filter(this::isArrivalAfterDeparture).collect(Collectors.toList());
    }

    @Override
    public boolean isArrivalAfterDeparture(Flight flight) {
        List<Segment> segments = flight.getSegments();
        for (Segment segment : segments) {
            if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Flight> removeLongConnections(List<Flight> flights, Duration maxConnectionTime) {
        return flights.stream()
                .filter((Flight flight) -> getConnectionsDuration(flight).compareTo(maxConnectionTime) <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public Duration getConnectionsDuration(Flight flight) {
        List<Segment> segments = flight.getSegments();
        int segmentsAmount = segments.size();
        if (segmentsAmount == 1) {
            return Duration.ZERO;
        } else {
            Duration connectionsDuration = Duration.ZERO;
            for (int i = 0; i <= segmentsAmount - 2; i++) {
                Duration duration = Duration
                        .between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
                connectionsDuration = connectionsDuration.plus(duration);
            }
            return connectionsDuration;
        }
    }

    //Extra filters
    @Override
    public List<Flight> filterByDepartureDay(List<Flight> flights, LocalDate date) {
        return flights.stream()
                .filter((Flight flight) -> flight.getSegments().get(0).getDepartureDate().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getFlightsWithMaxAmountOfConnections(List<Flight> flights, Integer maxAmountOfConnections) {
        return flights.stream()
                .filter((Flight flight) -> flight.getSegments().size() - 1 <= maxAmountOfConnections)
                .collect(Collectors.toList());
    }
}