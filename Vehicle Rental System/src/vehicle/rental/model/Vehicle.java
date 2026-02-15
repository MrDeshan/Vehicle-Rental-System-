package vehicle.rental.model;

// Vehicle.java
// Basic vehicle class for rental system

public abstract class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private int year;
    private double dailyRateLKR; // Price in LKR
    private boolean available;

    public Vehicle(String vehicleId, String brand, String model, int year, double dailyRateLKR) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyRateLKR = dailyRateLKR;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getDailyRateLKR() {
        return dailyRateLKR;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract String getType();

    public abstract double calculateRentalCost(int days);

    @Override
    public String toString() {
        return String.format("%s - %d %s %s - Rs. %.2f/day",
                getType(), year, brand, model, dailyRateLKR);
    }
}
