package org.example;

// our main class :D
public class Main
{
    public static void main( String[] args )
    {
        Car car1 = CarFactory.createCar("Sedan", "2022", "Toyota - Prado", 400, "Standard", 0);
        car1.displayCarInfo();



    }
}
