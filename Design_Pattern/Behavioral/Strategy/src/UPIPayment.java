public class UPIPayment implements PaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using UPI");
    }
}