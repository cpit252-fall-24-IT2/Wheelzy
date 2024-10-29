package org.example;
// same as the SUV , Sedan has different types (Eco/Standard/Luxury)
public class Sedan extends Car {
    private String type;
    public Sedan(String make, String model, double price, String type) {
        super(make, model, price);
        this.type = type;
    }

    @Override
    public void displayCarInfo() {
        System.out.println("Sedan: " + make + " " + model + ", Price: " + price + " SAR per Day");
    }
}
