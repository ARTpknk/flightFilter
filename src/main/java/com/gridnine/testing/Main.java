package com.gridnine.testing;

import com.gridnine.testing.controller.FilterController;
import com.gridnine.testing.controller.FilterControllerImpl;
import com.gridnine.testing.flightBuilder.FlightBuilder;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.FlightFilterSpecification;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FilterController filterController = new FilterControllerImpl();
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Remove past flights");
        FlightFilterSpecification flightFilter1 = new FlightFilterSpecification(LocalDateTime.now(),
                false,
                null,
                null,
                null);
        System.out.println(filterController.filterFlights(flights, flightFilter1) + "\n");

        System.out.println("Remove flights with arrival before departure");
        FlightFilterSpecification flightFilter2 = new FlightFilterSpecification(null,
                true,
                null,
                null,
                null);
        System.out.println(filterController.filterFlights(flights, flightFilter2) + "\n");

        System.out.println("Get flights with connections less than 2 hours");
        FlightFilterSpecification flightFilter3 = new FlightFilterSpecification(null,
                false,
                Duration.ofHours(2),
                null,
                null);
        System.out.println(filterController.filterFlights(flights, flightFilter3) + "\n");

        System.out.println("All filters together:");
        FlightFilterSpecification flightFilter4 = new FlightFilterSpecification(LocalDateTime.now(),
                true,
                Duration.ofHours(2),
                null,
                null);
        System.out.println(filterController.filterFlights(flights, flightFilter4));
    }
}