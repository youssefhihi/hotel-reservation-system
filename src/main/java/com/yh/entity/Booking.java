package com.yh.entity;

import com.yh.enums.RoomType;

import java.time.LocalDate;

public class Booking {
    private static long SEQ = 0;
    private final Long id;
    private User  user;
    private long     roomNumber;
    private RoomType roomType;
    private int      pricePerNight;
    private LocalDate checkIn;
    private LocalDate  checkOut;
    private Integer  totalCost;

    private Booking(){
        this.id = ++SEQ;
    }

    public Long getId(){ return id; }

    public long getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public User getUser() {
        return user;
    }


    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public static Booking create(
            User user, Room room,
            LocalDate checkIn, LocalDate checkOut,
            int totalCost
    ) {

        Booking b = new Booking();
        b.user                 = user;
        b.roomNumber           = room.getRoomNumber();
        b.roomType    = room.getType();
        b.pricePerNight= room.getPricePerNight();
        b.checkIn  = checkIn;
        b.checkOut = checkOut;
        b.totalCost= totalCost;
        return b;
    }

}
