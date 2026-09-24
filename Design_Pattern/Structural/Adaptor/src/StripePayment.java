public class StripePayment implements PaymentProcessor{
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Stripe");
    }
}
