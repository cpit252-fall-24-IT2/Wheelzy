package org.example;

// the abstract class for SUV and Sedan
public class Car {
    public final String make;
    public final String model;
    public final double price;

    public Car(String make, String model, double price) {
        this.make = make;
        this.model = model;
        this.price = price;
    }
//Builder**
    //    private Car(Builder builder) {
//        this.make = builder.setMake();
//        this.model = builder.setModel();
//        this.price = builder.setPrice();
//    }

    public void displayCarInfo() {
        System.out.println("The car is");
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