package vehicle.rental.factory;

import vehicle.rental.model.*;

// VehicleFactory.java
// Factory Pattern - Creates vehicles without client knowing concrete classes

public class VehicleFactory {

    public static Vehicle createVehicle(String type, String id, String brand, String model,
            int year, double dailyRate, Object extraParam) {

        switch (type.toLowerCase()) {
            case "car":
                if (extraParam instanceof Integer) {
                    int seats = (Integer) extraParam;
                    return new Car(id, brand, model, year, dailyRate, seats);
                }
                break;

            case "van":
                if (extraParam instanceof Integer) {
                    int capacity = (Integer) extraParam;
                    return new Van(id, brand, model, year, dailyRate, capacity);
                }
                break;

            case "truck":
                if (extraParam instanceof Double) {
                    double loadCapacity = (Double) extraParam;
                    return new Truck(id, brand, model, year, dailyRate, loadCapacity);
                }
                break;

            default:
                System.out.println("Unknown vehicle type: " + type);
                return null;
        }
        return null;
    }
}
