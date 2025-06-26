package com.yh.ui;

import com.yh.entity.User;
import com.yh.exceptions.UserNotFoundException;
import com.yh.service.UserService;
import com.yh.utils.Input;

import java.util.List;

public class UserUI {
    private final UserService userService;

    public UserUI(UserService userService) {
        this.userService = userService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n USER MANAGEMENT");
            System.out.println("1. Create New User");
            System.out.println("2. View All Users");
            System.out.println("3. Get User");
            System.out.println("4. Back to Main Menu");
            int choice = Input.intInput("Enter your choice: ");
            switch (choice) {
                case 1 -> createUser();
                case 2 -> printAllUsers();
                case 3 -> getUser();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void createUser() {
        int balance = Input.intInput("Enter user balance: ");
        User user =userService.setUser(balance);
        System.out.println("User created successfully.");
        printUsers(List.of(user));
    }

    public void printAllUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        printUsers(users);
    }


    public void getUser(){
        int id = Input.intInput("Enter user ID: ");
        try {
            User user = userService.getUser(id);
            printUsers(List.of(user));
        }catch (UserNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void  printUsers(List<User> users){
        System.out.println("\n USERS LIST");
        System.out.printf("%-10s | %-10s\n", "USER ID", "BALANCE");
        System.out.println("-----------------------------");
        for (User user : users) {
            System.out.printf("%-10d | %-10d\n", user.getId(), user.getBalance());
        }
    }
}
