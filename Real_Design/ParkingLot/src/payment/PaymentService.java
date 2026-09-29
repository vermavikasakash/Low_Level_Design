package payment;

public class PaymentService {

    private PaymentMethod paymentMethod;

    public PaymentService(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean pay(double amount) {
        return paymentMethod.pay(amount);
    }
}
