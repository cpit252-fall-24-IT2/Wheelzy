package org.example;

import java.io.*;
import java.util.*;

public class AuthSystem {
    private final String FILE_PATH = "login.txt";
    private ArrayList<User> users;

    public AuthSystem() {
        this.users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        try (Scanner reader = new Scanner(new File(FILE_PATH))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                if (data.length == 5) {
                    User user = new User(data[0], data[1], data[2], data[3], data[4]);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    public void register(String username, String password, String phoneNumber, String email, String address) throws IOException {
        if (findUserByUsername(username) != null) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        if (findUserByPhoneNumber(phoneNumber) != null) {
            throw new IllegalArgumentException("Phone number is already in use.");
        }
        if (findUserByEmail(email) != null) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        User newUser = new User(username, password, phoneNumber, email, address);
        users.add(newUser);
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(newUser.toFile() + "\n");
        }
    }

    private User findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    private User findUserByPhoneNumber(String phoneNumber) {
        return users.stream()
                .filter(user -> user.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    private User findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public User login(String username, String password) {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("Username not found.");
        }
        if (!user.validatePassword(password)) {
            throw new IllegalArgumentException("Incorrect password.");
        }
        return user;
    }
}
