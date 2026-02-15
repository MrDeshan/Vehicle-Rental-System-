package vehicle.rental.observer;

// SMSNotifier.java
// Another concrete observer

public class SMSNotifier implements RentalObserver {
    private String phoneNumber;

    public SMSNotifier(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String message) {
        System.out.println("[SMS] Sent to " + phoneNumber + ": " + message);
    }
}
