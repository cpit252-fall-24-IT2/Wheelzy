package org.example;

public class Main {
    public static void main(String[] args) {
        CarRentalSystem system = CarRentalSystem.getInstance();
        system.start();
        system.processUserInput();
    }
}
