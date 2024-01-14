package com.gridnine.testing.exceptions;

public class NegativeAmountOfConnections extends RuntimeException {
    public NegativeAmountOfConnections(String message) {
        super(message);
    }
}