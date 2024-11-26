package org.example;

// Singleton Class  :)

import java.util.*;

public class CarRentalSystem {

    private static CarRentalSystem instance;
    private List<Car> availableCars;

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

    public void addCar(Car car) {
        availableCars.add(car);
        System.out.println("Car added successfully!");
    }

    public void showAvailableCars() {
        if (availableCars.isEmpty()) {
            System.out.println("No cars available for rent.");
        } else {
            for (int i = 0; i < availableCars.size(); i++) {
                System.out.print((i + 1) + ". ");
                availableCars.get(i).displayCarInfo();
            }
        }
    }

}
