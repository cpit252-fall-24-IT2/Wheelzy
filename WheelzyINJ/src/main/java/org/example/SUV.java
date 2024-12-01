package org.example;
// SUV cars have different types like : Sportage (5 seats), Ford Flex (7 seats), or Chevrolet Suburban (9 seats)
import java.time.LocalDate;

public class SUV extends Car {
    private int numOfSeats;

    public SUV(String make, String model, double price, int numOfSeats, String owner, LocalDate availableFrom, LocalDate availableTo) {
        super(make, model, price, owner, availableFrom, availableTo);
        this.numOfSeats = numOfSeats;
    }

    public int getNumOfSeats() {
        return this.numOfSeats;
    }

    @Override
    public void displayCarInfo() {
        System.out.println("ID: " + id + " | SUV: " + make + " " + model + ", Seats: " + numOfSeats + ", Price: " + price + " SAR per Day");
        System.out.println("Available From: " + availableFrom + " To: " + availableTo);
    }
}

