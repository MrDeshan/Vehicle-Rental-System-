package vehicle.rental.service;

import vehicle.rental.model.*;
import vehicle.rental.observer.*;
import java.util.*;

public class RentalManager {
    private static RentalManager instance;
    private List<Rental> rentals;
    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<RentalObserver> observers;

    // Private constructor - prevents instantiation
    private RentalManager() {
        rentals = new ArrayList<>();
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        observers = new ArrayList<>();
    }

    // Get singleton instance
    public static RentalManager getInstance() {
        if (instance == null) {
            instance = new RentalManager();
            System.out.println("[SYSTEM] RentalManager initialized");
        }
        return instance;
    }

    // Add observer
    public void addObserver(RentalObserver observer) {
        observers.add(observer);
    }

    // Notify all observers
    private void notifyObservers(String message) {
        for (RentalObserver observer : observers) {
            observer.update(message);
        }
    }

    // Add vehicle to fleet
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        notifyObservers("New vehicle added: " + vehicle.toString());
    }

    // Add customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
        notifyObservers("New customer registered: " + customer.getName());
    }

    // Get available vehicles
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> available = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                available.add(v);
            }
        }
        return available;
    }

    // Rent a vehicle
    public Rental rentVehicle(String rentalId, Customer customer, Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            notifyObservers("Vehicle " + vehicle.getVehicleId() + " not available");
            return null;
        }

        vehicle.setAvailable(false);
        Rental rental = new Rental(rentalId, customer, vehicle, days);
        rentals.add(rental);

        notifyObservers("Rental created: " + customer.getName() + " rented " + vehicle.getModel() +
                " for " + days + " days (Rs. " + rental.getTotalCost() + ")");

        return rental;
    }

    // Create rental using IDs
    public void createRental(String rentalId, String customerId, String vehicleId, int days) {
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) {
                customer = c;
                break;
            }
        }

        Vehicle vehicle = null;
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equals(vehicleId)) {
                vehicle = v;
                break;
            }
        }

        if (customer == null) {
            System.out.println("✗ Customer not found!");
            return;
        }
        if (vehicle == null) {
            System.out.println("✗ Vehicle not found!");
            return;
        }

        Rental rental = rentVehicle(rentalId, customer, vehicle, days);
        if (rental == null) {
            System.out.println("✗ Rental failed!");
        }
    }

    // Return a vehicle
    public void returnVehicle(String rentalId) {
        for (Rental rental : rentals) {
            if (rental.getRentalId().equals(rentalId)) {
                rental.setReturned(true);
                rental.getVehicle().setAvailable(true);
                notifyObservers("Vehicle returned: " + rental.getVehicle().getModel());
                return;
            }
        }
    }

    // Display all vehicles
    public void displayVehicles() {
        System.out.println("\n=== Available Vehicles ===");
        List<Vehicle> available = getAvailableVehicles();
        if (available.isEmpty()) {
            System.out.println("No vehicles available");
        } else {
            for (Vehicle v : available) {
                System.out.println("- " + v);
            }
        }
    }

    // Display active rentals
    public void displayRentals() {
        System.out.println("\n=== Current Rentals ===");
        if (rentals.isEmpty()) {
            System.out.println("No active rentals");
        } else {
            for (Rental r : rentals) {
                if (!r.isReturned()) {
                    System.out.println("- " + r);
                }
            }
        }
    }

    // Display all customers
    public void displayCustomers() {
        System.out.println("\n=== Registered Customers ===");
        if (customers.isEmpty()) {
            System.out.println("No customers registered");
        } else {
            for (Customer c : customers) {
                System.out.println(
                        "- ID: " + c.getCustomerId() + " | Name: " + c.getName() + " | Phone: " + c.getPhone());
            }
        }
    }

    // Display system statistics
    public void displayStatistics() {
        System.out.println("\n=== System Statistics ===");
        System.out.println("Total Vehicles: " + vehicles.size());
        System.out.println("Available Vehicles: " + getAvailableVehicles().size());
        System.out.println("Total Customers: " + customers.size());
        System.out.println("Total Rentals: " + rentals.size());

        double totalRevenue = 0;
        for (Rental r : rentals) {
            totalRevenue += r.getTotalCost();
        }
        System.out.printf("Total Revenue: Rs. %.2f\n", totalRevenue);
    }
}
