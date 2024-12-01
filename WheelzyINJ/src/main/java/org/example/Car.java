package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// the abstract class for SUV and Sedan
public abstract class Car {
    private static int idCounter = 1;
    protected int id;
    public final String make;
    public final String model;
    public final double price;
    protected LocalDate availableFrom;
    protected LocalDate availableTo;
    private List<Observer> observers = new ArrayList<>();
    protected String owner;

    protected Car(String make, String model, double price, String owner, LocalDate availableFrom, LocalDate availableTo) {
        this.id = idCounter++;
        this.make = make;
        this.model = model;
        this.price = price;
        this.owner = owner;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    protected void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
//Builder**
    //    private Car(Builder builder) {
//        this.make = builder.setMake();
//        this.model = builder.setModel();
//        this.price = builder.setPrice();
//    }

    public abstract void displayCarInfo() ;

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
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


    public void setAvailability(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        this.availableFrom = from;
        this.availableTo = to;
    }

//    public static class Builder {
//        private String make;
//        private String model;
//        private double price;
//
//        public Builder setMake(String make) {
//            this.make = make;
//            return this;
//        }
//
//        public Builder setModel(String model) {
//            this.model = model;
//            return this;
//        }
//
//        public Builder setPrice(double price) {
//            this.price = price;
//            return this;
//        }
//
//
//        public Car build() {
//            return new Car(this);
//        }
//    }
}