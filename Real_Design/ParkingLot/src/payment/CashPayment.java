package payment;

public class CashPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println("Cash payment successful: ₹" + amount);
        return true;
    }
}