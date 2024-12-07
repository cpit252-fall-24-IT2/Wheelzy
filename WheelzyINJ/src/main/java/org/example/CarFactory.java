package org.example;

import java.time.LocalDate;

public class CarFactory {
    public static Car getCar(String type, String make, String model, double pricePerDay, String owner, String additionalAttribute, LocalDate availableFrom, LocalDate availableTo) {
        switch (type.toLowerCase()) {
            case "sedan":
                return new Sedan(make, model, pricePerDay, additionalAttribute, owner, availableFrom, availableTo);
            case "suv":
                int numOfSeats;
                try {
                    numOfSeats = Integer.parseInt(additionalAttribute);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number of seats for SUV.");
                }
                return new SUV(make, model, pricePerDay, numOfSeats, owner, availableFrom, availableTo);
            default:
                throw new IllegalArgumentException("Invalid car type. Supported types are 'Sedan' and 'SUV'.");
        }
    }
}
