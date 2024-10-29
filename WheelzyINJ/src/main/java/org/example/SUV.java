package org.example;
// SUV cars have different types like : Sportage (5 seats), Ford Flex (7 seats), or Chevrolet Suburban (9 seats)
public class SUV extends Car {
    private int numOfSeats;
    public SUV(String make, String model, double price, int numOfSeats ) {
        super(make, model, price);
        this.numOfSeats = numOfSeats;

    }

    @Override
    public void displayCarInfo() {
        System.out.println("SUV: " + make + " " + model + ", Price: " + price+ " SAR per Day ");
    }
}
