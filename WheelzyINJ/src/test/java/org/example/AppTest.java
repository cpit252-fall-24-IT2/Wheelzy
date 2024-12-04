package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class AppTest {
    private AuthSystem authSystem;
    private CarService carService;
    private CarRentalSystem carRentalSystem;

    @Before
    public void setUp() throws IOException {
        resetUserFile(); // إعادة تعيين ملف المستخدمين قبل كل اختبار
        authSystem = new AuthSystem(); // تهيئة النظام بعد إعادة التعيين
        carService = new CarService();
        carRentalSystem = CarRentalSystem.getInstance();
    }

    // طريقة لإعادة تعيين ملف `login.txt`
    private void resetUserFile() throws IOException {
        File userFile = new File("login.txt");
        if (userFile.exists()) {
            FileWriter writer = new FileWriter(userFile, false); // الكتابة فوق الملف بمحتوى فارغ
            writer.write(""); // تفريغ محتويات الملف
            writer.close();
        }
    }

    // اختبارات AuthSystem
    @Test
    public void testRegisterAndLogin() throws Exception {
        authSystem.register("testUser", "password123", "1234567890", "test@example.com", "Test Address");
        User user = authSystem.login("testUser", "password123");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterWithDuplicatePhoneNumber() throws Exception {
        authSystem.register("user1", "password123", "1234567890", "user1@example.com", "Address1");
        authSystem.register("user2", "password456", "1234567890", "user2@example.com", "Address2"); // نفس رقم الهاتف
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterWithDuplicateEmail() throws Exception {
        authSystem.register("user1", "password123", "1234567890", "user1@example.com", "Address1");
        authSystem.register("user2", "password456", "0987654321", "user1@example.com", "Address2"); // نفس البريد الإلكتروني
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoginWithNonexistentUser() {
        authSystem.login("nonexistentUser", "password123"); // تسجيل الدخول بمستخدم غير موجود
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoginWithWrongPassword() throws Exception {
        authSystem.register("user1", "password123", "1234567890", "user1@example.com", "Address1");
        authSystem.login("user1", "wrongPassword");
    }

    // اختبارات CarService
    @Test
    public void testAddCarWithValidDates() {
        Car car = new Sedan("Toyota", "Camry", 150.0, "Standard", "Owner1",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10));
        carService.addCar(car);
        assertNotNull(carService.getCarById(car.getId()));
    }

    @Test
    public void testGenerateReceipt() {
        ReceiptGenerator receiptGenerator = new ReceiptGenerator();
        Car car = new Sedan("Toyota", "Camry", 150.0, "Standard", "Owner1",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10));
        LocalDate startDate = LocalDate.of(2024, 1, 2);
        LocalDate endDate = LocalDate.of(2024, 1, 5);

        // No assertion needed, just ensure no exceptions occur and receipt displays
        receiptGenerator.generateReceipt(car, startDate, endDate);
    }

    @Test
    public void testNotificationManager() {
        NotificationManager notificationManager = new NotificationManager();
        notificationManager.addNotification("user1", "Your car has been rented.");
        notificationManager.addNotification("user1", "Another notification.");
        notificationManager.addNotification("user2", "A different user's notification.");

        // Capture the output
        notificationManager.displayNotifications("user1"); // Ensure messages for user1 are displayed and cleared
        notificationManager.displayNotifications("user2"); // Ensure messages for user2 are displayed and cleared
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddCarWithInvalidDates() {
        Car car = new Sedan("Toyota", "Camry", 150.0, "Standard", "Owner1",
                LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 1)); // تواريخ غير صالحة
        carService.addCar(car);
    }

    @Test
    public void testDisplayAvailableCarsExcludingOwner() {
        Car car1 = new Sedan("Toyota", "Camry", 200.0, "Luxury", "Ali",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15));
        Car car2 = new SUV("Nissan", "Patrol", 400.0, 5, "Ahmed",
                LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 20));
        carService.addCar(car1);
        carService.addCar(car2);

        // يجب أن يعرض فقط سيارات أحمد
        carService.displayAvailableCarsExcludingOwner("Ali");
    }

    @Test
    public void testGetCarByNonexistentId() {
        Car car = carService.getCarById(9999); // معرف غير موجود
        assertNull(car); // يجب أن يكون null
    }

    // اختبارات CarRentalSystem
    @Test
    public void testInitialize() {
        carRentalSystem.initialize();
        assertEquals(2, carRentalSystem.getCarService().getCarsByOwner("Ali").size()
                + carRentalSystem.getCarService().getCarsByOwner("Ahmed").size());
    }

    @Test
    public void testRentCarWithValidDates() {
        Car car = new Sedan("Toyota", "Yaris", 100.0, "Standard", "Owner1",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15));
        carService.addCar(car);

        LocalDate startDate = LocalDate.of(2024, 1, 2);
        LocalDate endDate = LocalDate.of(2024, 1, 5);

        carService.splitCarAvailability(car, startDate, endDate);

        // التحقق من توفر السيارة بعد الاستئجار
        assertEquals(LocalDate.of(2024, 1, 6), car.getAvailableFrom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRentCarOutsideAvailability() {
        Car car = new SUV("Ford", "Escape", 300.0, 7, "Owner2",
                LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 15));
        carService.addCar(car);

        carService.splitCarAvailability(car, LocalDate.of(2024, 2, 20), LocalDate.of(2024, 2, 25)); // فترة خارج التوفر
    }

    @Test
    public void testRegisterWithDuplicateUsernameAndEmail() throws Exception {
        authSystem.register("testUser", "password123", "1234567890", "test@example.com", "Test Address");
        try {
            authSystem.register("testUser", "password1234", "0987654321", "test@example.com", "Another Address");
            fail("Expected IllegalArgumentException for duplicate username and email.");
        } catch (IllegalArgumentException e) {
            assertEquals("Username is already taken.", e.getMessage());
        }
    }


    @Test
    public void testGetCarByIdWithInvalidId() {
        assertNull(carService.getCarById(9999)); // اختبار معرف غير موجود
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSplitCarAvailabilityWithInvalidDates() {
        Car car = new Sedan("Toyota", "Camry", 150.0, "Standard", "Owner1",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10));
        carService.addCar(car);

        // هذا يجب أن يرمي استثناء
        carService.splitCarAvailability(car, LocalDate.of(2024, 1, 11), LocalDate.of(2024, 1, 5));
    }


    @Test
    public void testDisplayAvailableCarsExcludingOwnerWithNoCars() {
        // عندما لا توجد سيارات مملوكة للمالك الحالي
        carService.displayAvailableCarsExcludingOwner("NonexistentOwner");
    }

    @Test
    public void testInitializeWithNoCars() {
        carRentalSystem.initialize();
        assertTrue(carRentalSystem.getCarService().getCarsByOwner("NonexistentOwner").isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterWithInvalidPhoneNumber() throws Exception {
        authSystem.register("invalidPhoneUser", "password123", "123", "user@example.com", "Address"); // رقم غير صالح
    }

}
