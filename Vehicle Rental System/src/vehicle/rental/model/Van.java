package vehicle.rental.model;

// Van.java
public class Van extends Vehicle {
    private int capacity;

    public Van(String vehicleId, String brand, String model, int year, double dailyRateLKR, int capacity) {
        super(vehicleId, brand, model, year, dailyRateLKR);
        this.capacity = capacity;
    }

    @Override
    public String getType() {
        return "Van";
    }

    @Override
    public double calculateRentalCost(int days) {
        return getDailyRateLKR() * days;
    }

    public int getCapacity() {
        return capacity;
    }
}
