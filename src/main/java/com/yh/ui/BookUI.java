package com.yh.ui;

import com.yh.entity.Booking;
import com.yh.exceptions.BookingInvalidException;
import com.yh.exceptions.RoomNotFoundException;
import com.yh.exceptions.UserNotFoundException;
import com.yh.service.BookingService;
import com.yh.utils.Input;

import java.time.LocalDate;
import java.util.List;

public class BookUI {
    private final BookingService bookingService;

    public BookUI(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public void showMenu() {
        while (true) {
            System.out.println("\nBOOKING MANAGEMENT");
            System.out.println("1. Book a Room");
            System.out.println("2. View All Bookings");
            System.out.println("3. Back to Main Menu");

            int choice = Input.intInput("Enter your choice: ");
            switch (choice) {
                case 1 -> bookRoom();
                case 2 -> printAllBookings();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void bookRoom() {

            long userId = Input.intInput("Enter User ID: ");

            int roomNumber = Input.intInput("Enter Room Number: ");

            LocalDate checkIn = Input.dateInput("Enter Check-In Date (YYYY-MM-DD): ");

            LocalDate checkOut = Input.dateInput("Enter Check-Out Date (YYYY-MM-DD): ");

            try {
                bookingService.bookRoom(userId, roomNumber, checkIn, checkOut);
                System.out.println("Booking successful.");
            } catch (
                    BookingInvalidException |
                     RoomNotFoundException |
                     UserNotFoundException e
            ) {
                System.out.println("Booking failed: " + e.getMessage());
            }
    }

    public void printAllBookings() {
        List<Booking> bookings = bookingService.getAll();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\nBOOKINGS LIST");
        System.out.printf("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s\n", "USER ID", "ROOM #", "TYPE", "CHECK-IN", "CHECK-OUT", "TOTAL");
        System.out.println("----------------------------------------------------------------------");
        for (Booking b : bookings) {
            System.out.printf("%-10d | %-10d | %-10s | %-10s | %-10s | %-10d\n",
                    b.getUser().getId(),
                    b.getRoomNumber(),
                    b.getRoomType(),
                    b.getCheckIn(),
                    b.getCheckOut(),
                    b.getTotalCost()
            );
        }
    }



}
