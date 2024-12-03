
package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarService {

    private List<Car> cars; // Use List to store cars

    public CarService() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null.");
        }
        cars.add(car);
    }

    public void addCarFromInput(Scanner scanner, String ownerUsername) {
        try {
            System.out.print("Enter car make (e.g Toyota): ");
            String make = scanner.nextLine();
            if (!make.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Invalid car make. Please enter letters only (e.g., Toyota).");
            }

            System.out.print("Enter car model (letters and numbers allowed)(e.g Camry 2020): ");
            String model = scanner.nextLine();
            if (!model.matches("^[a-zA-Z0-9 ]+$")) {
                throw new IllegalArgumentException("Invalid car model. Please enter letters and/or numbers (e.g., Camry 2020).");
            }

            System.out.print("Enter car price per day (positive number): ");
            double price = scanner.nextDouble();
            if (price <= 0) {
                throw new IllegalArgumentException("Price must be a positive number.");
            }
            scanner.nextLine(); // Consume newline

            System.out.print("Enter car type (SUV/Sedan): ");
            String type = scanner.nextLine();
            if (!type.equalsIgnoreCase("SUV") && !type.equalsIgnoreCase("Sedan")) {
                throw new IllegalArgumentException("Invalid car type. Please enter 'SUV' or 'Sedan'.");
            }

            System.out.print("Enter availability start date (YYYY-MM-DD): ");
            LocalDate availableFrom = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter availability end date (YYYY-MM-DD): ");
            LocalDate availableTo = LocalDate.parse(scanner.nextLine());
            if (!availableTo.isAfter(availableFrom)) {
                throw new IllegalArgumentException("End date must be after start date.");
            }

            String additionalAttribute = "";
            if (type.equalsIgnoreCase("SUV")) {
                System.out.print("Enter number of seats (positive integer): ");
                int numOfSeats = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (numOfSeats <= 0) {
                    throw new IllegalArgumentException("Number of seats must be a positive integer.");
                }
                additionalAttribute = String.valueOf(numOfSeats);
            } else if (type.equalsIgnoreCase("Sedan")) {
                System.out.print("Enter sedan type (e.g., Standard, Luxury): ");
                additionalAttribute = scanner.nextLine();
            }

            Car car = CarFactory.getCar(type, make, model, price, ownerUsername, additionalAttribute, availableFrom, availableTo);
            addCar(car);
            System.out.println("Car added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayAvailableCarsExcludingOwner(String ownerUsername) {
        System.out.println("\nAvailable cars:");
        cars.stream()
                .filter(car -> !car.getOwner().equals(ownerUsername))
                .forEach(Car::displayCarInfo);
    }

    public Car getCarById(int id) {
        return cars.stream()
                .filter(car -> car.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Car> getCarsByOwner(String ownerUsername) {
        List<Car> ownerCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getOwner().equals(ownerUsername)) {
                ownerCars.add(car);
            }
        }
        return ownerCars;
    }

    public void splitCarAvailability(Car car, LocalDate start, LocalDate end) {
        if (start.isAfter(car.getAvailableTo()) || end.isBefore(car.getAvailableFrom())) {
            throw new IllegalArgumentException("Invalid rental period. Car is not available for the selected dates.");
        }

        // Adjust car availability after the rental period
        if (end.isBefore(car.getAvailableTo())) {
            car.setAvailability(end.plusDays(1), car.getAvailableTo());
        }

        // Adjust car availability before the rental period
        if (start.isAfter(car.getAvailableFrom())) {
            car.setAvailability(car.getAvailableFrom(), start.minusDays(1));
        }
    }
}
