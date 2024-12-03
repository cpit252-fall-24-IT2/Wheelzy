package org.example;

import org.junit.Test;


import java.time.LocalDate;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testCarCreation() {
        SUV suv = new SUV("Toyota", "Highlander", 300.0, 7, "John", LocalDate.now(), LocalDate.now().plusDays(10));
        assertEquals("Toyota", suv.getMake());
        assertEquals("Highlander", suv.getModel());
        assertEquals(7, suv.getNumOfSeats());
    }

    @Test
    public void testSedanCreation() {
        Sedan sedan = new Sedan("Honda", "Civic", 150.0, "Standard", "Jane", LocalDate.now(), LocalDate.now().plusDays(5));
        assertEquals("Honda", sedan.getMake());
        assertEquals("Civic", sedan.getModel());
        assertEquals("Standard", sedan.getType());
    }

    @Test
    public void testUserRegistration() {
        AuthSystem authSystem = new AuthSystem();
        try {
            authSystem.register("testuser", "password123", "0501234567", "test@example.com", "Test Address");
        } catch (Exception e) {
            fail("Exception thrown during registration: " + e.getMessage());
        }

        User user = authSystem.login("testuser", "password123");
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
    }

    @Test
    public void testDuplicateUserRegistration() {
        AuthSystem authSystem = new AuthSystem();
        try {
            authSystem.register("duplicateuser", "password123", "0501234567", "duplicate@example.com", "Address");
            authSystem.register("duplicateuser", "password456", "0501234568", "duplicate2@example.com", "Address");
            fail("Expected exception for duplicate username.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testCarFactory() {
        Car car = CarFactory.getCar("SUV", "Ford", "Escape", 200.0, "Owner1", "5", LocalDate.now(), LocalDate.now().plusDays(7));
        assertNotNull(car);
        assertTrue(car instanceof SUV);
        assertEquals("Ford", car.getMake());
    }

    @Test
    public void testCarRentalSystemSingleton() {


    }

    @Test
    public void testCarAvailabilitySplit() {
        CarService carService = new CarService();
        LocalDate now = LocalDate.now();
        SUV suv = new SUV("Jeep", "Wrangler", 250.0, 5, "Owner", now, now.plusDays(10));
        carService.addCar(suv);
        carService.splitCarAvailability(suv, now.plusDays(3), now.plusDays(6));

        Car firstSplit = carService.getCarById(2); // Assuming IDs increment by 1
        assertNotNull(firstSplit);
        assertEquals(now.plusDays(3), firstSplit.getAvailableFrom());
        assertEquals(now.plusDays(6), firstSplit.getAvailableTo());
    }
}
