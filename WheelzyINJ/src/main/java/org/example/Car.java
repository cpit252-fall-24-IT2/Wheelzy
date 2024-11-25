package org.example;

// the abstract class for SUV and Sedan
public class Car {
    private final String make;
    private final String model;
    private final double price;

    private Car(Builder builder) {
        this.make = builder.setMake();
        this.model = builder.setModel();
        this.price = builder.setPrice();
    }

    public void displayCarInfo() {

    }


    public class Builder {
        private String make;
        private String model;
        private double price;

        public Builder setMake(String make) {
            this.make = make;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }


        public Car build() {
            return new Car(this);
        }
    }
}