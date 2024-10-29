package org.example;

// in Development.... :)

public class CarSingleton {

    private static CarSingleton instance;
    public String value;

    private CarSingleton() {
    }


    public static CarSingleton getInstance(String value) {
        if (instance == null) {
            instance = new CarSingleton();
        }
        return instance;
    }

}
