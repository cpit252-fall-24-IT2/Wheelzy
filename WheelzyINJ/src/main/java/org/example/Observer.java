
package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Observer {
    private final List<String> notifications = new ArrayList<>();

    public void addNotification(String message) {
        notifications.add(message);
    }

   public abstract void displayNotifications(String message);
//        if (notifications.isEmpty()) {
//            System.out.println("No new notifications.");
//        } else {
//            System.out.println("Notifications:");
//            for (String notification : notifications) {
//                System.out.println("- " + notification);
//            }
//            notifications.clear();
//        }
//    }
}
