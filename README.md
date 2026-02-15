# Vehicle Rental System

## Project Overview

The Vehicle Rental System is an interactive Java application demonstrating three critical design patterns: **Factory Pattern**, **Singleton Pattern**, and **Observer Pattern**. The system manages vehicle rentals in Sri Lanka with pricing in LKR (Sri Lankan Rupees) and automatically sends email and SMS notifications for rental events.

## Features

- **Add Vehicle:** Register new vehicles (Car, Van, Truck) with unique specifications using the Factory Pattern
- **Register Customer:** Capture customer information including ID, name, phone, and address
- **Create Rental:** Automatically calculate rental costs based on vehicle type and duration with polymorphic pricing
- **Return Vehicle:** Mark vehicles as available and trigger observer notifications
- **View Data:** Display all vehicles, customers, and rental records from the centralized Singleton
- **System Statistics:** View total vehicles, customers, rentals, and revenue generated
- **Automatic Notifications:** Email and SMS notifications sent automatically via Observer Pattern when rentals are created or returned

## Design Patterns Implemented

### 1. Factory Pattern (Creational)
The `VehicleFactory` class encapsulates all vehicle creation logic, allowing new vehicle types to be added without modifying existing code. When users select a vehicle type, the factory creates the appropriate object based on type parameters.

### 2. Singleton Pattern (Creational)
The `RentalManager` class ensures only one instance manages all system data—vehicles, customers, rentals, and observers—maintaining data consistency throughout the application lifecycle. The private constructor prevents instantiation, and getInstance() provides global access.

### 3. Observer Pattern (Behavioral)
The `RentalObserver` interface defines a contract for notification handlers. `EmailNotifier` and `SMSNotifier` implementations automatically receive and process notifications, demonstrating loose coupling between the rental system and notification mechanisms.

## Project Structure

```
Vehicle Rental System/
├── vehicle.rental.model/
│   ├── Vehicle.java (abstract)
│   ├── Car.java
│   ├── Van.java
│   ├── Truck.java
│   ├── Customer.java
│   └── Rental.java
├── vehicle.rental.factory/
│   └── VehicleFactory.java
├── vehicle.rental.service/
│   └── RentalManager.java
├── vehicle.rental.observer/
│   ├── RentalObserver.java (interface)
│   ├── EmailNotifier.java
│   └── SMSNotifier.java
└── VehicleRentalSystem.java (main)
```

## How to Run

1. **Compile:** `javac *.java`
2. **Execute:** `java VehicleRentalSystem`
3. **Select Option:** Choose from 1-9 menu options
4. **Follow Prompts:** Enter required information dynamically (no hardcoded data)
5. **View Results:** Check console output for confirmation and notifications

## Requirements

- Java 8 or higher
- No external dependencies required

