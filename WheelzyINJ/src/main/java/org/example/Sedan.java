
package org.example;

import java.time.LocalDate;

public class Sedan extends Car {
    private final String type; // e.g., Standard, Luxury

    public Sedan(String make, String model, double pricePerDay, String type, String owner, LocalDate availableFrom, LocalDate availableTo) {
        super(make, model, pricePerDay, owner, availableFrom, availableTo);
        this.type = type;
    }

    @Override
    public void displayCarInfo() {
        System.out.printf("ID: %d | Sedan: %s %s (%s), Price: %.2f SAR per Day%nAvailable From: %s To: %s%n",
                id, make, model, type, pricePerDay, availableFrom, availableTo);
    }

    public String getType() {
        return type;
    }
}
