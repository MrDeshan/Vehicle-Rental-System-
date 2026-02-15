package vehicle.rental.model;

// Rental.java
// Model for rental transaction

public class Rental {
    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;
    private double totalCost;
    private boolean returned;

    public Rental(String rentalId, Customer customer, Vehicle vehicle, int rentalDays) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.totalCost = vehicle.calculateRentalCost(rentalDays);
        this.returned = false;
    }

    public String getRentalId() {
        return rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return rentalId + " - " + customer.getName() + " rented " + vehicle.getModel() +
                " for " + rentalDays + " days (Rs. " + totalCost + ")";
    }
}
