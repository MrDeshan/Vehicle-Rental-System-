package vehicle.rental.main;

import vehicle.rental.model.*;
import vehicle.rental.service.*;
import vehicle.rental.factory.*;
import vehicle.rental.observer.*;
import java.util.Scanner;
import java.util.List;

public class VehicleRentalSystem {
    private static RentalManager manager;
    private static Scanner scanner;

    public static void main(String[] args) {
        manager = RentalManager.getInstance();
        scanner = new Scanner(System.in);

        // Setup observers
        setupObservers();

        // Main menu
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addVehicleMenu();
                    break;
                case 2:
                    registerCustomerMenu();
                    break;
                case 3:
                    createRentalMenu();
                    break;
                case 4:
                    returnVehicleMenu();
                    break;
                case 5:
                    manager.displayVehicles();
                    break;
                case 6:
                    manager.displayCustomers();
                    break;
                case 7:
                    manager.displayRentals();
                    break;
                case 8:
                    manager.displayStatistics();
                    break;
                case 9:
                    running = false;
                    System.out.println("\nThank you for using Vehicle Rental System!");
                    break;
                default:
                    System.out.println("✗ Invalid choice! Please try again.\n");
            }
        }
        scanner.close();
    }

    private static void setupObservers() {
        // Add email and SMS notification observers
        manager.addObserver(new EmailNotifier("rental@company.com"));
        manager.addObserver(new SMSNotifier("0771234567"));
    }

    private static void displayMainMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   SRI LANKA VEHICLE RENTAL SYSTEM      ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n  1. Add Vehicle");
        System.out.println("  2. Register Customer");
        System.out.println("  3. Create Rental");
        System.out.println("  4. Return Vehicle");
        System.out.println("  5. View All Vehicles");
        System.out.println("  6. View All Customers");
        System.out.println("  7. View All Rentals");
        System.out.println("  8. System Statistics");
        System.out.println("  9. Exit");
        System.out.println();
    }

    private static void addVehicleMenu() {
        System.out.println("\n========== ADD VEHICLE ==========");

        String vehicleId = getStringInput("Vehicle ID (e.g., V001): ");

        System.out.println("\nVehicle Types:");
        System.out.println("  1. Car");
        System.out.println("  2. Van");
        System.out.println("  3. Truck");
        int vehicleType = getIntInput("Select vehicle type (1-3): ");

        String type = "";
        switch (vehicleType) {
            case 1:
                type = "car";
                break;
            case 2:
                type = "van";
                break;
            case 3:
                type = "truck";
                break;
            default:
                System.out.println("Invalid type!");
                return;
        }

        String brand = getStringInput("Brand (e.g., Toyota): ");
        String model = getStringInput("Model (e.g., Corolla): ");
        int year = getIntInput("Year (e.g., 2022): ");
        double dailyRate = getDoubleInput("Daily Rate (LKR): ");

        Object extraParam = null;
        if (type.equals("car")) {
            int seats = getIntInput("Number of Seats: ");
            extraParam = seats;
        } else if (type.equals("van")) {
            int capacity = getIntInput("Passenger Capacity: ");
            extraParam = capacity;
        } else if (type.equals("truck")) {
            double loadCapacity = getDoubleInput("Load Capacity (Tons): ");
            extraParam = loadCapacity;
        }

        Vehicle vehicle = VehicleFactory.createVehicle(type, vehicleId, brand, model, year, dailyRate, extraParam);
        if (vehicle != null) {
            manager.addVehicle(vehicle);
        } else {
            System.out.println("✗ Failed to create vehicle!");
        }
    }

    private static void registerCustomerMenu() {
        System.out.println("\n========== REGISTER CUSTOMER ==========");

        String customerId = getStringInput("Customer ID (e.g., C001): ");
        String name = getStringInput("Full Name: ");
        String phone = getStringInput("Phone Number (e.g., 0771234567): ");
        String address = getStringInput("Address (City): ");

        Customer customer = new Customer(customerId, name, phone, address);
        manager.addCustomer(customer);
    }

    private static void createRentalMenu() {
        System.out.println("\n========== CREATE RENTAL ==========");

        // Display available vehicles
        System.out.println("\nAvailable Vehicles:");
        List<Vehicle> available = manager.getAvailableVehicles();
        if (available.isEmpty()) {
            System.out.println("No vehicles available!");
            return;
        }
        for (int i = 0; i < available.size(); i++) {
            System.out.println((i + 1) + ". " + available.get(i));
        }

        // Display customers
        System.out.println("\nRegistered Customers:");
        manager.displayCustomers();

        String rentalId = getStringInput("Rental ID (e.g., R001): ");
        String customerId = getStringInput("Customer ID: ");
        String vehicleId = getStringInput("Vehicle ID: ");
        int days = getIntInput("Rental Days: ");

        manager.createRental(rentalId, customerId, vehicleId, days);
    }

    private static void returnVehicleMenu() {
        System.out.println("\n========== RETURN VEHICLE ==========");

        // Display active rentals
        System.out.println("\nActive Rentals:");
        manager.displayRentals();

        String rentalId = getStringInput("Rental ID to return: ");
        manager.returnVehicle(rentalId);
    }

    // Helper methods for input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.\n");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a valid number.\n");
            }
        }
    }
}
