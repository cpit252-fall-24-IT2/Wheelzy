
package org.example;

import java.time.LocalDate;

public class SUV extends Car {
    private final int numOfSeats;

    public SUV(String make, String model, double pricePerDay, int numOfSeats, String owner, LocalDate availableFrom, LocalDate availableTo) {
        super(make, model, pricePerDay, owner, availableFrom, availableTo);
        if (numOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be a positive integer.");
        }
        this.numOfSeats = numOfSeats;
    }

    @Override
    public void displayCarInfo() {
        System.out.printf("ID: %d | SUV: %s %s, Seats: %d, Price: %.2f SAR per Day%nAvailable From: %s To: %s%n",
                id, make, model, numOfSeats, pricePerDay, availableFrom, availableTo);
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }
}
