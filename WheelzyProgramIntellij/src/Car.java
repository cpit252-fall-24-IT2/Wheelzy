
public abstract class Car {
    protected  String make;
    protected String model;
    protected double price;


    public Car(String make, String model, double price) {
        this.make = make;
        this.model = model;
        this.price = price;
    }

    public abstract void displayCarInfo();
}
