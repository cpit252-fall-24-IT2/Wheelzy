package org.example;

import java.util.ArrayList;
import java.util.List;

// our main class :D
public class Main {
    private static AuthSystem authSystem = new AuthSystem();
    private static List<Car> cars = new ArrayList<>();

    public static void main(String[] args) {
        CarRentalSystem rentalSystem = CarRentalSystem.getInstance();
        rentalSystem.start();
        rentalSystem.initialize();
        // rentalSystem.processUserInput();

        Car car1 = CarFactory.createCar("Sedan", "2022", "Toyota - Prado", 400, "Standard", 0);
        car1.displayCarInfo();



    }
}
