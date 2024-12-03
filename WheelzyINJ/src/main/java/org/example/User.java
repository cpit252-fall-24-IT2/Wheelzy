package org.example;

public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;

    public User(String username, String password, String phoneNumber, String email, String address) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        if (!phoneNumber.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Invalid phone number format. Must contain 10-15 digits.");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }

        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String toFile() {
        return username + "," + password + "," + phoneNumber + "," + email + "," + address;
    }
}
