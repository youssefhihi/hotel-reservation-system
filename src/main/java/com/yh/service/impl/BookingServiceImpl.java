package com.yh.service.impl;

import com.yh.entity.Booking;
import com.yh.entity.Room;
import com.yh.entity.User;
import com.yh.exceptions.BookingInvalidException;
import com.yh.service.BookingService;
import com.yh.service.RoomService;
import com.yh.service.UserService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    private final List<Booking> bookings = new ArrayList<>();

    private final UserService userService;
    private final RoomService roomService;

    public BookingServiceImpl(UserService uSvc, RoomService rSvc) {
        this.userService  = uSvc;
        this.roomService  = rSvc;
    }

    public void bookRoom(long userId,
                         long roomNumber,
                         LocalDate checkIn,
                         LocalDate checkOut) {

        if (checkIn.isBefore(LocalDate.now())) {
            throw new BookingInvalidException(" checkIn must not be in the past");
        }

        if (!checkOut.isAfter(checkIn))
            throw new BookingInvalidException("check-out must be after check-in");

        Room room = roomService.getRoom(roomNumber);
        User user = userService.getUser(userId);

        boolean overlap = bookings.stream()
                .filter(b -> b.getRoomNumber() == roomNumber)
                .anyMatch(b -> !(checkOut.isBefore(b.getCheckIn()) ||
                        checkIn.isAfter (b.getCheckOut())));

        if (overlap) throw new BookingInvalidException("room not free");

        int nights = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
        int cost   = nights * room.getPricePerNight();
        if (cost > user.getBalance())
            throw new BookingInvalidException("insufficient balance");

        user.debit(cost);
        bookings.add(Booking.create(user, room, checkIn, checkOut, cost));
    }

    public List<Booking> getAll() { return bookings; }
}
