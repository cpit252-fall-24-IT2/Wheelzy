package org.example;

// Factory Design pattern to design different types of cars (SUV/Sedan)
import java.time.LocalDate;

public class CarFactory {
    public static Car getCar(String type, String make, String model, double price, String owner,
                             String additionalAttribute, LocalDate availableFrom, LocalDate availableTo) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        if (type.equalsIgnoreCase("SUV")) {
            int numOfSeats = Integer.parseInt(additionalAttribute);
            return new SUV(make, model, price, numOfSeats, owner, availableFrom, availableTo);
        } else if (type.equalsIgnoreCase("Sedan")) {
            return new Sedan(make, model, price, additionalAttribute, owner, availableFrom, availableTo);
        }

        System.err.println("Unknown car type");
        return null;
    }
}

