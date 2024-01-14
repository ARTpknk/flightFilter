package com.gridnine.testing.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightFilterSpecification {
    private LocalDateTime theEarliestTimeSpotForDeparture;
    private boolean areFlightsWithArrivalBeforeDepartureRemoving;
    private Duration maxConnectionTime;
    private LocalDate departureDate;
    private Integer maxAmountOfConnections;

    public FlightFilterSpecification(LocalDateTime theEarliestTimeSpotForDeparture,
                        boolean areFlightsWithArrivalBeforeDepartureRemoving, Duration maxConnectionTime,
                        LocalDate departureDate, Integer maxAmountOfConnections) {
        this.theEarliestTimeSpotForDeparture = theEarliestTimeSpotForDeparture;
        this.areFlightsWithArrivalBeforeDepartureRemoving = areFlightsWithArrivalBeforeDepartureRemoving;
        this.maxConnectionTime = maxConnectionTime;
        this.departureDate = departureDate;
        this.maxAmountOfConnections = maxAmountOfConnections;
    }

    public LocalDateTime getTheEarliestTimeSpotForDeparture() {
        return theEarliestTimeSpotForDeparture;
    }

    public boolean isAreFlightsWithArrivalBeforeDepartureRemoving() {
        return areFlightsWithArrivalBeforeDepartureRemoving;
    }

    public Duration getMaxConnectionTime() {
        return maxConnectionTime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public Integer getMaxAmountOfConnections() {
        return maxAmountOfConnections;
    }

    public void setTheEarliestTimeSpotForDeparture(LocalDateTime theEarliestTimeSpotForDeparture) {
        this.theEarliestTimeSpotForDeparture = theEarliestTimeSpotForDeparture;
    }

    public void setAreFlightsWithArrivalBeforeDepartureRemoving(boolean areFlightsWithArrivalBeforeDepartureRemoving) {
        this.areFlightsWithArrivalBeforeDepartureRemoving = areFlightsWithArrivalBeforeDepartureRemoving;
    }

    public void setMaxConnectionTime(Duration maxConnectionTime) {
        this.maxConnectionTime = maxConnectionTime;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setMaxAmountOfConnections(Integer maxAmountOfConnections) {
        this.maxAmountOfConnections = maxAmountOfConnections;
    }
}