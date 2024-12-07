package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class CarRentalSystem {
    private static CarRentalSystem instance;
    private final AuthSystem authSystem;
    private final CarService carService;
    private final NotificationManager notificationManager;
    private final ReceiptGenerator receiptGenerator;
    private final Scanner scanner;

    private CarRentalSystem() {
        this.authSystem = new AuthSystem();
        this.carService = new CarService();
        this.notificationManager = new NotificationManager();
        this.receiptGenerator = new ReceiptGenerator();
        this.scanner = new Scanner(System.in);
        carService.loadCarsFromFile();
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

    public void processUserInput() {
        while (true) {
            printMainMenu();
            int choice = readIntFromScanner();
            switch (choice) {
                case 1 -> handleLogin();
                case 2 -> handleRegister();
                case 3 -> {
                    System.out.println("Thank you for using Wheelzy!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n1. Login\n2. Register\n3. Exit");
        System.out.print("Choose an option: ");
    }

    private int readIntFromScanner() {
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private void handleLogin() {
        String username = promptInput("Enter username: ");
        String password = promptInput("Enter password: ");

        try {
            User user = authSystem.login(username, password);
            System.out.println("Welcome to Wheelzy , " + username);
            notifyOwnerOnLogin(user);
            userMenu(user);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleRegister() {
        String username = promptInput("Enter username: ");
        String password = promptInput("Enter password: ");
        String phone = promptInput("Enter phone: ");
        String email = promptInput("Enter email: ");
        String address = promptInput("Enter address: ");

        try {
            authSystem.register(username, password, phone, email, address);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String promptInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private void notifyOwnerOnLogin(User user) {
        notificationManager.displayNotifications(user.getUsername());
    }

    public void userMenu(User user) {
        while (true) {
            printUserMenu();
            int choice = readIntFromScanner();
            switch (choice) {
                case 1 -> rentCar(user);
                case 2 -> carService.addCarFromInput(scanner, user.getUsername());
                case 3 -> {
                    System.out.println("Logging out...");
                    return;
                }
                case 0 -> exitProgram();
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printUserMenu() {
        System.out.println("\n1. View and Rent Cars");
        System.out.println("2. Add Your Car");
        System.out.println("3. Logout");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void exitProgram() {
        System.out.println("Exiting program...");
        System.exit(0);
    }

    public void rentCar(User user) {
        int availableCount = carService.displayAvailableCarsExcludingOwner(user.getUsername());

        if (availableCount == 0) {
            System.out.println("Returning to the menu...");
            return;
        }

        if (confirmAction("Do you want to rent a car? (yes/no): ")) {
            int carId = getCarIdFromUser();
            Car car = validateCarId(carId);
            if (car == null) return;
            LocalDate startDate = getRentalDate(car.getAvailableFrom(), car.getAvailableTo());
            LocalDate endDate = getRentalEndDate(car, startDate);
            receiptGenerator.generateReceipt(car, startDate, endDate);
            confirmingRental(startDate, endDate, car, user);
        } else {
            System.out.println("Returning to the menu...");
        }
    }

    private boolean confirmAction(String message) {
        System.out.print(message);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

    private int getCarIdFromUser() {
        System.out.print("Enter the ID of the car you want to rent: ");
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private Car validateCarId(int carId) {
        Car car = carService.getCarById(carId);
        if (car == null) {
            System.out.println("Invalid car ID. Returning to the menu...");
        }
        return car;
    }

    private LocalDate getRentalDate(LocalDate minDate, LocalDate maxDate) {
        while (true) {
            System.out.print("Enter rental start date (YYYY-MM-DD): ");
            try {
                LocalDate date = LocalDate.parse(scanner.nextLine());
                if (date.isBefore(minDate) || date.isAfter(maxDate)) {
                    System.out.printf("Date must be between %s and %s.%n", minDate, maxDate);
                    continue;
                }
                return date;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }
    }

    private LocalDate getRentalEndDate(Car car, LocalDate startDate) {
        while (true) {
            System.out.print("Enter rental end date (YYYY-MM-DD): ");
            try {
                LocalDate endDate = LocalDate.parse(scanner.nextLine());
                if (endDate.isBefore(startDate) || endDate.isAfter(car.getAvailableTo())) {
                    System.out.printf("End date must be after %s and no later than %s.%n", startDate, car.getAvailableTo());
                    continue;
                }
                return endDate;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }
    }

    private void confirmingRental(LocalDate startDate, LocalDate endDate, Car car, User user) {
        if (confirmAction("Do you want to confirm the rental? (yes/no): ")) {
            carService.splitCarAvailability(car, startDate, endDate);

            String owner = car.getOwner();
            String message = String.format("Your car %s %s has been rented by %s from %s to %s.",
                    car.getMake(), car.getModel(), user.getUsername(), startDate, endDate);
            notificationManager.addNotification(owner, message);
            System.out.printf("Car rented successfully from %s to %s.%n", startDate, endDate);
            System.out.println("The courier will contact you via mobile phone number : " + user.getPhoneNumber() + " .");
        } else {
            System.out.println("Rental cancelled. Returning to the menu...");
        }
    }

    public CarService getCarService() {
        return this.carService;
    }
}
