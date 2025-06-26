package com.yh.service.impl;

import com.yh.entity.Room;
import com.yh.enums.RoomType;
import com.yh.exceptions.RoomNotFoundException;
import com.yh.service.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {

    private final List<Room> rooms = new ArrayList<>();

    @Override
    public void setRoom(Long roomNumber, RoomType type, Integer price) {
        Optional<Room> opt = rooms.stream()
                .filter(r -> r.getRoomNumber().equals(roomNumber))
                .findFirst();

        if (opt.isPresent()) {
            opt.get().update(type, price);
            return;
        }
        Room created = Room.create(roomNumber,type, price);
        rooms.add(created);
    }

    @Override
    public Room getRoom(Long roomNumber) {
        return rooms.stream()
                .filter(r -> r.getRoomNumber().equals(roomNumber))
                .findFirst()
                .orElseThrow(() -> new RoomNotFoundException(roomNumber));
    }

    @Override
    public List<Room> getAll() { return rooms; }
}
