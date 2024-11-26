package org.example;

// Singleton Class  :)

import java.util.*;
import java.util.Scanner;

public class CarRentalSystem {

    private static CarRentalSystem instance;
    private AuthSystem authSystem;
    private CarService carService;
    private Scanner scanner;

    private CarRentalSystem() {
        availableCars = new ArrayList<>();
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
        carService.addCar(new Sedan("Toyota", "Camry", 200, "Standard", "Ahmed"));
        carService.addCar(new SUV("Ford", "Explorer", 300, 7, "Ali"));
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

        try {
            User user = authSystem.login(username, password);
            if (user != null) {
                processUserActions(user);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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

    private void processUserActions(User user) {
        while (true) {
            System.out.println("\nWelcome, " + user.getUsername());
            System.out.println("1. View available cars\n2. Rent your car\n3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    carService.displayAvailableCars();
                    System.out.print("Do you want to rent a car? (yes/no): ");
                    String rentOption = scanner.nextLine();
                    if (rentOption.equalsIgnoreCase("yes")) {
                        System.out.print("Enter the ID of the car you want to rent: ");
                        int carId = scanner.nextInt();
                        scanner.nextLine();
                        if (carService.rentCar(carId, user.getUsername())) {
                            System.out.println("Car rented successfully!");
                        } else {
                            System.out.println("Car not found or already rented.");
                        }
                    }
                }
                case 2:
                    carService.addCarFromInput(scanner);
                case 3: {
                    System.out.println("Logging out...");
                    return;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


}
