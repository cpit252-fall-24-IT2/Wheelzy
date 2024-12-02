package org.example;

import java.io.*;
import java.util.*;

// till now, we're trying :) so is not working ._.

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
            System.out.println("Error loading users: " + e.getMessage());
        }
    }


    // after register , store to log in file
    public void register(String username, String password, String phoneNumber, String email, String address) throws IOException {
        if (findUserByUsername(username) != null) {
            System.out.println("Username is already taken!");
            return;
        }

        if (findUserByPhoneNumber(phoneNumber) != null) {
            System.out.println("Phone number is already in use!");
            return;
        }
        if (findUserByEmail(email) != null) {
            System.out.println("email is already in use!");
            return;
        }

        User newUser = new User(username, password, phoneNumber, email, address);
        users.add(newUser);
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(newUser.toFile() + "\n");
            System.out.println("User registered successfully.");
        }
    }

    private User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    private User findUserByPhoneNumber(String phoneNumber) {
        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return user;
            }
        }
        return null;
    }

    private User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }


    // login will check the login file if exist or not or it valid or not
    public User login(String username, String password) throws IOException {

            while (reader.hasNextLine()) {
                String line= reader.nextLine();
                String[] data = line.split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    System.out.println("Login successful! Role: " + data[3]);
                    return new User.Builder()
                            .setUsername(data[0])
                            .setPassword(data[1])
                            .setPhoneNumber(data[2])
                            .setRole(data[3])
                            .build();
                }
            }
            System.out.println("Invalid username or password.");

        return null;

    }
 
}

