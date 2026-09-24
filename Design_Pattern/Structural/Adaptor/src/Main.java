

class Main {
    static void main(String[] args) {

        PayPalSDK payPalSDK = new PayPalSDK();

        PaymentProcessor processor = new StripePayment();
        PaymentProcessor processor2 = new PayPalAdapter(payPalSDK);

        PaymentService service = new PaymentService(processor);
        PaymentService service2 = new PaymentService(processor2);

        service.makePayment(5000);
        service2.makePayment(5000);

    }
}

