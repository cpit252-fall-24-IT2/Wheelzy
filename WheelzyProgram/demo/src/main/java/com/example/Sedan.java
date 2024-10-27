package com;

public class Sedan extends Car {
    private String type;
  public Sedan(String make, String model, double price, String type) {
      this.make = make;
      this.model = model;
      this.price = price;
      this.type = type;
  }

  @Override
  public void displayCarInfo() {
      System.out.println("Sedan: " + make + " " + model + ", Price: " + price + "SAR/Day");
  }
}
