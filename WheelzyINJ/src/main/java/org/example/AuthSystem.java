package org.example;

import java.io.*;
import java.util.*;

// till now, we're trying :) so is not working ._.

public class AuthSystem {
    private final String FILE_PATH = "login.txt";
    Scanner reader = new Scanner(FILE_PATH);

    // after register , store to log in file
    public void register(User user) throws IOException {
        try (FileWriter  writer = new FileWriter(FILE_PATH, true)) {
                if (!userExists(user.getUsername())) {
                    writer.write(user.toFile() + "\n");
                    System.out.println("User registered successfully as a customer.");
                } else {
                    System.out.println("Username already exist ! , try " + user.getUsername() + (Math.random() * 10) + " " );
                }
    }
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

