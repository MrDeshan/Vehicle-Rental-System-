package vehicle.rental.model;

// Customer.java
// Simple customer class

public class Customer {
    private String customerId;
    private String name;
    private String phone;
    private String address;

    public Customer(String customerId, String name, String phone, String address) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name + " (" + phone + ")";
    }
}
