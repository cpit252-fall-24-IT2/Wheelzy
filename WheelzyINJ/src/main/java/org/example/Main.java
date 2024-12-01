package org.example;

import java.util.ArrayList;
import java.util.List;

// our main class :D
public class Main {

    public static void main(String[] args) {
        CarRentalSystem rentalSystem = CarRentalSystem.getInstance();
        rentalSystem.start();
        rentalSystem.initialize();
        rentalSystem.processUserInput();



    }
}
