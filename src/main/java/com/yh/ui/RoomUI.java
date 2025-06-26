package com.yh.ui;

import com.yh.entity.Room;
import com.yh.enums.RoomType;
import com.yh.exceptions.RoomNotFoundException;
import com.yh.service.RoomService;
import com.yh.utils.Input;

import java.util.List;

public class RoomUI {
    private final RoomService roomService;

    public RoomUI(RoomService roomService) {
        this.roomService = roomService;
    }
    public void showMenu() {
        while (true) {
            System.out.println("\nROOM MANAGEMENT");
            System.out.println("1. Create or Update Room");
            System.out.println("2. View All Rooms");
            System.out.println("3. View Room Details");
            System.out.println("4. Back to Main Menu");

            int choice = Input.intInput("Enter your choice: ");
            switch (choice) {
                case 1 -> setRoom();
                case 2 -> printAllRooms();
                case 3 -> getRoom();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void setRoom() {
        long number = Input.longInput("Enter Room Number: ");

        System.out.println("Choose Room Type:");
        System.out.println("1. STANDARD_SUITE");
        System.out.println("2. JUNIOR_SUITE");
        System.out.println("3. MASTER_SUITE");

        int typeChoice = Input.intInput("Enter your choice: ");

        RoomType type;
        switch (typeChoice) {
            case 1 -> type = RoomType.STANDARD_SUITE;
            case 2 -> type = RoomType.JUNIOR_SUITE;
            case 3 -> type = RoomType.MASTER_SUITE;
            default -> {
                System.out.println("Invalid type.");
                return;
            }
        }

        int price = Input.intInput("Enter price per night: ");

        roomService.setRoom(number, type, price);
        System.out.println("Room saved successfully.");
    }

    public void printAllRooms() {
        List<Room> rooms = roomService.getAll();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }

     printRooms(rooms);
    }

    public void getRoom() {
        long number = Input.longInput("Enter Room Number: ");
        try {
            Room room = roomService.getRoom(number);
            printRooms(List.of(room));
        }catch (RoomNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }
    }



    private void printRooms(List<Room> rooms) {
        System.out.println("\nROOMS LIST");
        System.out.printf("%-12s | %-15s | %-15s\n", "ROOM NUMBER", "ROOM TYPE", "PRICE/NIGHT");
        System.out.println("----------------------------------------------------------");
        for (Room room : rooms) {
            System.out.printf("%-12d | %-15s | %-15d\n", room.getRoomNumber(), room.getType(), room.getPricePerNight());
        }
    }
}
