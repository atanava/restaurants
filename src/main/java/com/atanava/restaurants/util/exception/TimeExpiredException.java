package com.atanava.restaurants.util.exception;

import java.time.DateTimeException;

public class TimeExpiredException extends DateTimeException {
    public TimeExpiredException(String message) {
        super(message);
    }
}
