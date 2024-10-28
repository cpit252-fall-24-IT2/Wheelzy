package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AuthSystem {
    private final String FILE_PATH = "login.txt";
    Scanner reader = new Scanner(FILE_PATH);

    // after register , store to login file
    public void register(User user) throws IOException {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
                if (!userExists(user.getUsername())) {
                    writer.write(user.toFile() + "\n");
                    System.out.println("User registered successfully as a customer.");
                } else {
                    System.out.println("Username already exist ! ");
                }
    }
    }

    // login will check the login file if exist or not or it valid or not
    public User login(String username, String password) throws IOException {
            String line;
            while ((line = reader.nextLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    System.out.println("Login successful! Role: " + data[3]);
                    return new User(data[0], data[1], data[2], data[3]);
                }
            }
            System.out.println("Invalid username or password.");

        return null;

    }
    // check while register if the username exists or not
    public boolean userExists(String username) throws IOException {
            String line;
            while ((line = reader.nextLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    return true;
                }
            }

        return false;
    }
}

