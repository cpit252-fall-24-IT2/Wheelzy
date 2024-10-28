

public class SUV extends Car {
    private int numOfSeats;

    public SUV(String make, String model, double price, int numOfSeats) {
        super(make, model, price);
        this.numOfSeats = numOfSeats;

    }

    @Override
    public void displayCarInfo() {
        System.out.println("SUV: " + make + " " + model + ", Price: $" + price);
    }
}
