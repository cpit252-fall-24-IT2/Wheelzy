package org.example;

public class CarFactory {
    public static Car createCar(String category, String make, String model, double price, String type, int numOfSeats) {
        switch (category.toLowerCase()) {
            // if the car is SUV , then we need to identify how many seats : 5/7/9
            case "suv":
                return new SUV(make, model, price, numOfSeats);
            // if the car is Sedan , then we need to identify which type of Sedan : Economy/Standard/Luxury
            case "sedan":
                return new Sedan(make, model, price, type);
            default:
                // throw an exception to terminal class to catch it later (not implemented yet =) )
                throw new IllegalArgumentException("Unknown car type: " + category);
        }
    }
}
