package payment;

import model.User;

import java.math.BigDecimal;

public interface PaymentService {

    boolean processPayment(User user, BigDecimal amount);
}