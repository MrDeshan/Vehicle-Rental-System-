package vehicle.rental.model;

// Car.java
public class Car extends Vehicle {
    private int seats;

    public Car(String vehicleId, String brand, String model, int year, double dailyRateLKR, int seats) {
        super(vehicleId, brand, model, year, dailyRateLKR);
        this.seats = seats;
    }

    @Override
    public String getType() {
        return "Car";
    }

    @Override
    public double calculateRentalCost(int days) {
        // Basic calculation
        return getDailyRateLKR() * days;
    }

    public int getSeats() {
        return seats;
    }
}
