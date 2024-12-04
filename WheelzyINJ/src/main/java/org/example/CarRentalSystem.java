
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
    }

    public static CarRentalSystem getInstance() {
        if (instance == null) {
            instance = new CarRentalSystem();
        }
        return instance;
    }

    public void initialize() {
        carService.addCar(new Sedan("Toyota", "Camry", 200.0, "Standard", "Ali", LocalDate.of(2024, 12, 9), LocalDate.of(2024, 12, 31)));
        carService.addCar(new SUV("Ford", "Explorer", 300.0, 7, "Ahmed", LocalDate.of(2024, 12, 5), LocalDate.of(2024, 12, 20)));
    }

    public void start() {
        System.out.println("Welcome to the Wheelzy Rental System!");
    }

    public void processUserInput() {
        while (true) {
            System.out.println("\n1. Login\n2. Register\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
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

    public void handleLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        try {
            User user = authSystem.login(username, password);
            System.out.println("Welcome to Wheelzy , "+ username);
            notifyOwnerOnLogin(user);
            userMenu(user);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void notifyOwnerOnLogin(User user) {
        notificationManager.displayNotifications(user.getUsername());
    }

    public void handleRegister() {
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

    public void userMenu(User user) {
        while (true) {
            System.out.println("\n1. View and Rent Cars\n2. Add Your Car\n3. Logout\n0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> rentCar(user);
                case 2 -> carService.addCarFromInput(scanner, user.getUsername());
                case 3 -> {
                    System.out.println("Logging out...");
                    return;
                }
                case 0 -> {
                    System.out.println("Exiting program...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public void rentCar(User user) {
        carService.displayAvailableCarsExcludingOwner(user.getUsername());
        System.out.print("\nDo you want to rent a car? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            System.out.print("Enter the ID of the car you want to rent: ");
            int carId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Car car = carService.getCarById(carId);
            if (car == null) {
                System.out.println("Invalid car ID. Returning to the menu...");
                return;
            }

            LocalDate startDate = null;
            LocalDate endDate = null;

            // Collect and validate rental start date
            while (true) {
                try {
                    System.out.print("Enter rental start date (YYYY-MM-DD): ");
                    startDate = LocalDate.parse(scanner.nextLine());
                    if (startDate.isBefore(car.getAvailableFrom()) || startDate.isAfter(car.getAvailableTo())) {
                        System.out.printf("Start date must be between %s and %s.%n",
                                car.getAvailableFrom(), car.getAvailableTo());
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
                    if (endDate.isBefore(startDate) || endDate.isAfter(car.getAvailableTo())) {
                        System.out.printf("End date must be after %s and no later than %s.%n",
                                startDate, car.getAvailableTo());
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            }


            receiptGenerator.generateReceipt(car, startDate, endDate);


            System.out.print("Do you want to confirm the rental? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes")) {

                carService.splitCarAvailability(car, startDate, endDate);


                String owner = car.getOwner();
                String message = String.format("Your car %s %s has been rented by %s from %s to %s.",
                        car.getMake(), car.getModel(), user.getUsername(), startDate, endDate);
                notificationManager.addNotification(owner, message);


                System.out.printf("Car rented successfully from %s to %s.%n", startDate, endDate);
                System.out.println("The courier will contact you via mobile phone number : "+user.getPhoneNumber()+" .");
            } else {
                System.out.println("Rental cancelled. Returning to the menu...");
            }
        } else {
            System.out.println("Returning to the menu...");
        }
    }
    public CarService getCarService() {
        return this.carService;
    }
}
