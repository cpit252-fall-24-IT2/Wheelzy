package org.example;

public class CarSingleton {

    private static CarSingleton instance;
    public String value;

    private CarSingleton(String value) {
        this.value = value;
    }


    public static CarSingleton getInstance(String value) {
        if (instance == null) {
            instance = new CarSingleton(value);
        }
        return instance;
    }

}
