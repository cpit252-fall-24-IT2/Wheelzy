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
        cars.add(car);
    }

    public void addCarFromInput(Scanner scanner, String ownerUsername) {
        System.out.print("Enter car make: ");
        String make = scanner.nextLine();
        System.out.print("Enter car model: ");
        String model = scanner.nextLine();
        System.out.print("Enter car price per day: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter car type (SUV/Sedan): ");
        String type = scanner.nextLine();
        System.out.print("Enter availability start date (YYYY-MM-DD): ");
        LocalDate availableFrom = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter availability end date (YYYY-MM-DD): ");
        LocalDate availableTo = LocalDate.parse(scanner.nextLine());

        String additionalAttribute = "";
        if (type.equalsIgnoreCase("SUV")) {
            System.out.print("Enter number of seats: ");
            additionalAttribute = scanner.nextLine();
        } else if (type.equalsIgnoreCase("Sedan")) {
            System.out.print("Enter sedan type (e.g., Standard, Luxury): ");
            additionalAttribute = scanner.nextLine();
        }

        Car car = CarFactory.getCar(type, make, model, price, ownerUsername, additionalAttribute, availableFrom, availableTo);
        if (car != null) {
            addCar(car);
            System.out.println("Car added successfully!");
        }
    }

    public void displayAvailableCarsExcludingOwner(String ownerUsername) {
        System.out.println("\nAvailable cars:");
        boolean found = false;
        for (Car car : cars) {
            // Check if the car is available and not owned by the user
            if (!car.getOwner().equals(ownerUsername) && car.getAvailableFrom().isBefore(car.getAvailableTo())) {
                car.displayCarInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cars available for rent (excluding your own cars).");
        }
    }


    public Car getCarById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public void splitCarAvailability(Car car, LocalDate start, LocalDate end) {
        Car newCar;
        if (car instanceof SUV) {
            SUV suv = (SUV) car;
            newCar = new SUV(suv.getMake(), suv.getModel(), suv.getPrice(), suv.getNumOfSeats(),
                    suv.getOwner(), start, end);
        } else if (car instanceof Sedan) {
            Sedan sedan = (Sedan) car;
            newCar = new Sedan(sedan.getMake(), sedan.getModel(), sedan.getPrice(), sedan.getType(),
                    sedan.getOwner(), start, end);
        } else {
            throw new IllegalArgumentException("Unknown car type.");
        }

        addCar(newCar); // Add the new car to the list
    }
}
