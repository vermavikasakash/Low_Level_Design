public class PayPalAdapter implements PaymentProcessor{
    private PayPalSDK payPalSDK;

    public PayPalAdapter(PayPalSDK payPalSDK) {
        this.payPalSDK = payPalSDK;
    }

    @Override
    public void pay(double amount) {
        payPalSDK.makePaymentInUSD(amount);
    }
}
