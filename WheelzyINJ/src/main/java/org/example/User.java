package org.example;

public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;

    public User(String username, String password, String phoneNumber, String email, String address) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
