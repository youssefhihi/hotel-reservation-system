package com.yh.exceptions;

public class BookingInvalidException extends RuntimeException {
    public BookingInvalidException(String message) {
        super(message);
    }
}
