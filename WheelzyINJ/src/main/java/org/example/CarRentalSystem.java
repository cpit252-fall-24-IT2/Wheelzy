package org.example;

// Singleton Class  :)

import java.time.LocalDate;
import java.util.*;
import java.util.Scanner;

public class CarRentalSystem {

    private static CarRentalSystem instance;
    private AuthSystem authSystem;
    private carService carService;
    private Scanner scanner;

    private CarRentalSystem() {
        this.authSystem = new AuthSystem();
        this.carService = new carService();
        this.scanner = new Scanner(System.in);
    }

    public static CarRentalSystem getInstance() {
        if (instance == null) {
            instance = new CarRentalSystem();
        }
        return instance;
    }

    public void start() {
        System.out.println("Welcome to the Wheelzy Rental System!");
    }

    public void initialize() {
        carService.addCar(new Sedan("Toyota", "Camry", 200.0, "Standard", "Ali",
                LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 31)));
        carService.addCar(new SUV("Ford", "Explorer", 300.0, 7, "Ahmed",
                LocalDate.of(2024, 12, 5), LocalDate.of(2024, 12, 20)));
    }

    public void processUserInput() {
        while (true) {
            System.out.println("\n1. Login\n2. Register\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleLogin();
                case 2:
                    handleRegister();
                case 3: {
                    System.out.println("Thank you for using Wheelzy! Goodbye!");
                    return;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = authSystem.login(username, password);
        if (user != null) {
            userMenu(user);
        }
    }

    private void userMenu(User user) {
        while (true) {
            System.out.println("\n1. View and Rent Cars\n2. Add Your Car\n3. Logout\n0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // عشان يسوي سطر جديد
            switch (choice) {
                case 1:
                    rentCar(user);
                case 2:
                    carService.addCarFromInput(scanner, user.getUsername());
                case 3: {
                    System.out.println("Logging out...");
                    return;
                }
                case 0: {
                    System.out.println("Exiting program...");
                    System.exit(0);
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleRegister() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter role (Customer/Provider): ");
        String role = scanner.nextLine();

        User user = new User.Builder()
                .setUsername(username)
                .setPassword(password)
                .setPhoneNumber(phone)
                .setRole(role)
                .build();

        try {
            authSystem.register(user);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
