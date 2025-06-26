package com.yh.service;

import com.yh.entity.Room;
import com.yh.enums.RoomType;

import java.util.List;

public interface RoomService {
    void setRoom(Long roomNumber, RoomType type, Integer price);
    Room getRoom(Long roomNumber);
    List<Room> getAll();
}
