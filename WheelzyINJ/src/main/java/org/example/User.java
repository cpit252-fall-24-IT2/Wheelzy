package org.example;

public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private String role;

    public User(String username, String password, String phoneNumber, String role) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String toFile() {
        return username + "," + password + "," + phoneNumber + "," + role;
    }
}
