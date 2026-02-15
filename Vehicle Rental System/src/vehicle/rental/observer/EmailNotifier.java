package vehicle.rental.observer;

// EmailNotifier.java
// Concrete Observer - Sends notifications

public class EmailNotifier implements RentalObserver {
    private String email;

    public EmailNotifier(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("[EMAIL] Sent to " + email + ": " + message);
    }
}
