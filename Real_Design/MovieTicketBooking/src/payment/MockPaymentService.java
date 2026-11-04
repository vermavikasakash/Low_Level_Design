package payment;

import model.User;

import java.math.BigDecimal;

public class MockPaymentService implements PaymentService {

    @Override
    public boolean processPayment(User user, BigDecimal amount) {
        System.out.println("Processing payment of ₹" + amount + " for user: " + user.getEmail());

        return false;
    }
}