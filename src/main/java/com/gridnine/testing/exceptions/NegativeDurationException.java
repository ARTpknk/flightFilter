package com.gridnine.testing.exceptions;

public class NegativeDurationException extends RuntimeException {
    public NegativeDurationException(String message) {
        super(message);
    }
}