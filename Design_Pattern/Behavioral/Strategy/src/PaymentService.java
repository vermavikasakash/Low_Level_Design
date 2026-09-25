public class PaymentService {

    private PaymentMethod paymentMethod;

    public PaymentService(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void pay(double amount) {
        paymentMethod.pay(amount);
    }
}