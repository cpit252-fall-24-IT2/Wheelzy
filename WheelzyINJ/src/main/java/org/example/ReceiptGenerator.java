
package org.example;

import java.time.LocalDate;

public class ReceiptGenerator {

    public void generateReceipt(Car car, LocalDate startDate, LocalDate endDate) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double totalCost = car.getPricePerDay() * days;
        System.out.println("\n--- Rental Receipt ---");
        System.out.printf("Car: %s %s%n", car.getMake(), car.getModel());
        System.out.printf("Rental Period: %s to %s%n", startDate, endDate);
        System.out.printf("Total Days: %d%n", days);
        System.out.printf("Daily Price: %.2f SAR%n", car.getPricePerDay());
        System.out.printf("Total Cost: %.2f SAR%n", totalCost);
        System.out.println("------------------------\n");
    }
}
