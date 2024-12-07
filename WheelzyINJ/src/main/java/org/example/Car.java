package org.example;

import java.time.LocalDate;

public abstract class Car {
    private static int idCounter = 1;
    protected int id;
    protected final String make;
    protected final String model;
    protected double pricePerDay;
    protected String owner;
    protected LocalDate availableFrom;
    protected LocalDate availableTo;

    protected Car(String make, String model, double pricePerDay, String owner, LocalDate availableFrom, LocalDate availableTo) {
        this.id = idCounter++;
        this.make = make;
        this.model = model;
        this.pricePerDay = Math.max(pricePerDay, 0);
        this.owner = owner;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    public abstract void displayCarInfo();

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public LocalDate getAvailableTo() {
        return availableTo;
    }

    public void setPricePerDay(double pricePerDay) {
        if (pricePerDay <= 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }
        this.pricePerDay = pricePerDay;
    }

    public void setAvailability(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        this.availableFrom = from;
        this.availableTo = to;
    }

    public String toFile() {
        String type = this instanceof Sedan ? "Sedan" : this instanceof SUV ? "SUV" : "Unknown";
        return type + "," + make + "," + model + "," + pricePerDay + "," + owner + "," + availableFrom + "," + availableTo + extraAttributes();
    }

    protected abstract String extraAttributes();

    public static void resetIdCounter() {
        idCounter = 1;
    }
}
