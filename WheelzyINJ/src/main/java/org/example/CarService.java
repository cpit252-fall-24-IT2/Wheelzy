package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarService {
    private List<Car> cars;
    private final String CARS_FILE = "cars.txt";

    public CarService() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car must not be null.");
        }
        if (car.getAvailableFrom().isAfter(car.getAvailableTo())) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
        cars.add(car);
        saveCarsToFile();
    }

    public void addCarFromInput(Scanner scanner, String ownerUsername) {
        try {
            String make = promptCarMake(scanner);
            String model = promptCarModel(scanner);
            double price = promptCarPrice(scanner);
            String type = promptCarType(scanner);
            LocalDate availableFrom = promptDate(scanner, "Enter availability start date (YYYY-MM-DD): ");
            LocalDate availableTo = promptEndDate(scanner, availableFrom);
            String additionalAttribute = promptAdditionalAttribute(scanner, type);

            Car car = CarFactory.getCar(type, make, model, price, ownerUsername, additionalAttribute, availableFrom, availableTo);
            addCar(car);
            System.out.println("Car added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String promptCarMake(Scanner scanner) {
        System.out.print("Enter car make (e.g Toyota): ");
        String make = scanner.nextLine();
        if (!make.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid car make. Please enter letters only (e.g., Toyota).");
        }
        return make;
    }

    private String promptCarModel(Scanner scanner) {
        System.out.print("Enter car model (letters and numbers allowed)(e.g Camry 2020): ");
        String model = scanner.nextLine();
        if (!model.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Invalid car model. Please enter letters and/or numbers (e.g., Camry 2020).");
        }
        return model;
    }

    private double promptCarPrice(Scanner scanner) {
        System.out.print("Enter car price per day (positive number): ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be a positive number.");
        }
        return price;
    }

    private String promptCarType(Scanner scanner) {
        System.out.print("Enter car type (SUV/Sedan): ");
        String type = scanner.nextLine();
        if (!type.equalsIgnoreCase("SUV") && !type.equalsIgnoreCase("Sedan")) {
            throw new IllegalArgumentException("Invalid car type. Please enter 'SUV' or 'Sedan'.");
        }
        return type;
    }

    private LocalDate promptDate(Scanner scanner, String message) {
        System.out.print(message);
        return LocalDate.parse(scanner.nextLine());
    }

    private LocalDate promptEndDate(Scanner scanner, LocalDate availableFrom) {
        LocalDate availableTo = promptDate(scanner, "Enter availability end date (YYYY-MM-DD): ");
        if (!availableTo.isAfter(availableFrom)) {
            throw new IllegalArgumentException("End date must be after start date.");
        }
        return availableTo;
    }

    private String promptAdditionalAttribute(Scanner scanner, String type) {
        if (type.equalsIgnoreCase("SUV")) {
            System.out.print("Enter number of seats (positive integer): ");
            int numOfSeats = scanner.nextInt();
            scanner.nextLine();
            if (numOfSeats <= 0) {
                throw new IllegalArgumentException("Number of seats must be a positive integer.");
            }
            return String.valueOf(numOfSeats);
        } else {
            System.out.print("Enter sedan type (e.g., Standard, Luxury): ");
            return scanner.nextLine();
        }
    }

    public int displayAvailableCarsExcludingOwner(String ownerUsername) {
        System.out.println("\nAvailable cars:");
        List<Car> availableCars = cars.stream()
                .filter(car -> !car.getOwner().equals(ownerUsername))
                .toList();

        if (availableCars.isEmpty()) {
            System.out.println("No available cars.");
        } else {
            availableCars.forEach(Car::displayCarInfo);
        }

        return availableCars.size();
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

        // تأجير كامل الفترة
        if (start.equals(car.getAvailableFrom()) && end.equals(car.getAvailableTo())) {
            cars.remove(car);
            saveCarsToFile();
            return;
        }

        if (end.isBefore(car.getAvailableTo())) {
            car.setAvailability(end.plusDays(1), car.getAvailableTo());
        }

        if (start.isAfter(car.getAvailableFrom())) {
            car.setAvailability(car.getAvailableFrom(), start.minusDays(1));
        }
        saveCarsToFile();
    }

    public void removeCar(Car car) {
        cars.remove(car);
        saveCarsToFile();
    }

    public void saveCarsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CARS_FILE))) {
            for (Car car : cars) {
                writer.println(car.toFile());
            }
        } catch (IOException e) {
            System.out.println("Error saving cars: " + e.getMessage());
        }
    }

    public void loadCarsFromFile() {
        cars.clear();
        File file = new File(CARS_FILE);
        if (!file.exists()) return;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 7) continue;
                String type = parts[0];
                String make = parts[1];
                String model = parts[2];
                double price = Double.parseDouble(parts[3]);
                String owner = parts[4];
                LocalDate from = LocalDate.parse(parts[5]);
                LocalDate to = LocalDate.parse(parts[6]);
                String additional = parts.length > 7 ? parts[7] : "";

                Car car = CarFactory.getCar(type, make, model, price, owner, additional, from, to);
                cars.add(car);
            }
        } catch (Exception e) {
            System.out.println("Error loading cars: " + e.getMessage());
        }
    }
}
