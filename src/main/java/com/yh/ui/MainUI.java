package com.yh.ui;

import com.yh.service.BookingService;
import com.yh.service.RoomService;
import com.yh.service.UserService;
import com.yh.service.impl.BookingServiceImpl;
import com.yh.service.impl.RoomServiceImpl;
import com.yh.service.impl.UserServiceImpl;
import com.yh.utils.Input;

public class MainUI {

    private final UserUI userUI;
    private final RoomUI roomUI;
    private final BookUI bookUI;

    public MainUI() {
        RoomService roomService = new RoomServiceImpl();
        UserService userService = new UserServiceImpl();
        BookingService bookingService = new BookingServiceImpl(userService, roomService);
        userUI = new UserUI(userService);
        roomUI = new RoomUI(roomService);
        bookUI = new BookUI(bookingService);
    }

    public void menu () {
        System.out.println(" ⚙ Welcome to Hotel Reservation System menu ");
        while (true) {
            System.out.println("1. User Management");
            System.out.println("2. Room Management");
            System.out.println("3. Booking Management");
            System.out.println("4. Exit");

            int userChoice = Input.intInput("enter your choice: ");

            switch (userChoice) {
                case 1 -> userUI.showMenu();
                case 2 -> roomUI.showMenu();
                case 3 -> bookUI.showMenu();
                case 4 -> {
                    System.out.println("Good bye");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }


}
