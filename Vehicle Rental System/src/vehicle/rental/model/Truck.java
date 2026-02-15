package vehicle.rental.model;

// Truck.java
public class Truck extends Vehicle {
    private double loadCapacityTons;

    public Truck(String vehicleId, String brand, String model, int year, double dailyRateLKR, double loadCapacity) {
        super(vehicleId, brand, model, year, dailyRateLKR);
        this.loadCapacityTons = loadCapacity;
    }

    @Override
    public String getType() {
        return "Truck";
    }

    @Override
    public double calculateRentalCost(int days) {
        // Trucks have additional charges
        double baseCost = getDailyRateLKR() * days;
        double additionalCharge = baseCost * 0.1; // 10% extra for truck
        return baseCost + additionalCharge;
    }

    public double getLoadCapacity() {
        return loadCapacityTons;
    }
}
