package com.gridnine.testing.controller;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.FlightFilterSpecification;

import java.util.List;

public interface FilterController {
    List<Flight> filterFlights(List<Flight> flights, FlightFilterSpecification flightFilter);
}