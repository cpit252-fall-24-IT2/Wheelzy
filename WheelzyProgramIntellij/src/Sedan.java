public class Sedan extends Car {
    private String type;
    private String make;
    private String model;
    private double price;

    public Sedan(String make, String model, double price, String type) {
        super(make, model, price);
        this.type = type;
    }

    @Override
    public void displayCarInfo() {
        System.out.println("Sedan: " + make + " " + model + ", Price: " + price + "SAR/Day");
    }
}
