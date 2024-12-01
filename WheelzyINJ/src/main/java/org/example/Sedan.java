package org.example;
// same as the SUV , Sedan has different types (Eco/Standard/Luxury)
import java.time.LocalDate;

public class Sedan extends Car {
    private String type;

    public Sedan(String make, String model, double price, String type, String owner, LocalDate availableFrom, LocalDate availableTo) {
        super(make, model, price, owner, availableFrom, availableTo);
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public void displayCarInfo() {
        System.out.println("ID: " + id + " | Sedan: " + make + " " + model + " (" + type + "), Price: " + price + " SAR per Day");
        System.out.println("Available From: " + availableFrom + " To: " + availableTo);
    }
}

