package com.laxman.evgridops.exception;

public class StationNotFoundException extends RuntimeException {

    public StationNotFoundException(String message) {
        super(message);
    }
}