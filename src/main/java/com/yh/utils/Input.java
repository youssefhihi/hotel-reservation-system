package com.yh.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {

  private static final Scanner scanner = new Scanner(System.in);

    public static Integer intInput(String prompt){
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (Exception e) {
                System.out.println("Error:  Enter a Valid Number");
                scanner.nextLine();
            }
        }

    }

    public static Long longInput(String prompt){
        while (true) {
            try {
                System.out.print(prompt);
                long input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (Exception e) {
                System.out.println("Error:  Enter a Valid Number");
                scanner.nextLine();
            }
        }

    }
    public static LocalDate dateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date. Please enter in YYYY-MM-DD format.");
            }
        }
    }
}
