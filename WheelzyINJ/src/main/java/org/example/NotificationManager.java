
package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationManager {
    private final Map<String, List<String>> notifications = new HashMap<>();

    public void addNotification(String recipient, String message) {
        notifications.computeIfAbsent(recipient, k -> new ArrayList<>()).add(message);
    }

    public void displayNotifications(String recipient) {
        List<String> userNotifications = notifications.get(recipient);
        if (userNotifications == null || userNotifications.isEmpty()) {
            System.out.println("No new notifications.");
            return;
        }
        System.out.println("Notifications:");
        for (String notification : userNotifications) {
            System.out.println("- " + notification);
        }
        userNotifications.clear(); // Clear notifications after displaying
    }
}
