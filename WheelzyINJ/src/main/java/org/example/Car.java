package org.example;
// the abstract class for SUV and Sedan
public abstract class Car {
    protected String make;
    protected String model;
    protected double price;

    protected Car(String make, String model, double price) {
        this.make = make;
        this.model = model;
        this.price = price;
    }

    public abstract void displayCarInfo();
}
