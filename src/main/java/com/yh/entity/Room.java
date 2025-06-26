package com.yh.entity;

import com.yh.enums.RoomType;

public class Room {
    private Long roomNumber;
    private RoomType type;
    private Integer pricePerNight;

    private Room(){}

    public Long getRoomNumber()          { return roomNumber; }
    public RoomType getType()           { return type; }
    public Integer getPricePerNight()       { return pricePerNight; }

    public void update(RoomType type, Integer pricePerNight) {
        this.type          = type;
        this.pricePerNight = pricePerNight;
    }

    public static Room create(Long roomNumber,RoomType type, Integer pricePerNight) {
        Room room = new Room();
        room.roomNumber = roomNumber;
        room.type = type;
        room.pricePerNight = pricePerNight;
        return room;
    }

}
