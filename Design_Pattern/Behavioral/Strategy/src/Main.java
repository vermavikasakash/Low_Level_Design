public class Main {
    public static void main(String[] args) {

        PaymentService service = new PaymentService(new CreditCardPayment());
        // Paying 500 using Credit Card
        service.pay(500);


        service.setPaymentMethod(new UPIPayment());
      // Paying 500 using UPI
        service.pay(400);

    }
}
