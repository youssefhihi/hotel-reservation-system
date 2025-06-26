package com.yh.service;

import com.yh.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    void bookRoom(long userId,
                  long roomNumber,
                  LocalDate checkIn,
                  LocalDate checkOut);
    List<Booking> getAll();
}
