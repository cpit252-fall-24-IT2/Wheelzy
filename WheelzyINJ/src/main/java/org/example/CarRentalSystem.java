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
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        try {
            authSystem.register(username, password, phone, email, address);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void rentCar() {
        carService.displayAvailableCarsExcludingOwner(user.getUsername());
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            System.out.print("Enter the ID of the car you want to rent: ");
            int carId = scanner.nextInt();
            scanner.nextLine();

            Car car = carService.getCarById(carId);
            if (car == null) {
                System.out.println("Invalid car ID. Returning to the menu...");
                return;
            }

            LocalDate startDate = null;
            LocalDate endDate = null;

            while (true) {
                try {
                    System.out.print("Enter rental start date (YYYY-MM-DD): ");
                    startDate = LocalDate.parse(scanner.nextLine());
                    if (startDate.isBefore(car.getAvailableFrom())) {
                        System.out.println("Start date cannot be before the available start date (" + car.getAvailableFrom() + ").");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter rental end date (YYYY-MM-DD): ");
                    endDate = LocalDate.parse(scanner.nextLine());
                    if (endDate.isAfter(car.getAvailableTo())) {
                        System.out.println("End date cannot be after the available end date (" + car.getAvailableTo() + ").");
                        continue;
                    }
                    if (endDate.isBefore(startDate)) {
                        System.out.println("End date cannot be before the start date (" + startDate + ").");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            }

            if (startDate.isAfter(car.getAvailableFrom())) {
                carService.splitCarAvailability(car, car.getAvailableFrom(), startDate.minusDays(1));
            }

            if (endDate.isBefore(car.getAvailableTo())) {
                carService.splitCarAvailability(car, endDate.plusDays(1), car.getAvailableTo());
            }


            car.setAvailability(endDate.plusDays(1), endDate.plusDays(1));
            System.out.println("Car rented successfully from " + startDate + " to " + endDate);


            notifyOwner(car.getOwner(), user, car, startDate, endDate);
        } else {
            System.out.println("Returning to the menu...");
        }

    }

    private void notifyOwner() {
    }
}
