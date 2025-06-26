package com.yh.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(Long roomNumber) {
        super("Room number " + roomNumber + " not found");
    }
}
