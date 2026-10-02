package payments;

public interface Payment {
    PaymentResult pay(int amountRequired);
}
