package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Observer {
    private final List<String> notifications = new ArrayList<>();

    public void addNotification(String message) {
        notifications.add(message);
    }

    public abstract void displayNotifications(String message);
}
