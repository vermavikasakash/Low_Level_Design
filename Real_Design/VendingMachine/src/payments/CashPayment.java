package payments;

import enums.Denomination;
import enums.PaymentStatus;

import java.util.HashMap;
import java.util.Map;

public class CashPayment implements Payment {

    private final Map<Denomination, Integer> insertedCash;
    private int insertedAmount;

    public CashPayment() {
        this.insertedCash = new HashMap<>();
    }
    // methods
    public void insertCash(Denomination denomination) {
        insertedAmount += denomination.getValue();
        insertedCash.merge(denomination, 1, Integer::sum);
        System.out.println("insertedCash : " + insertedCash);
    }

    @Override
    public PaymentResult pay(int amountRequired) {

        PaymentResult result;

        int remain = amountRequired - insertedAmount;

        if (remain == 0) {
            // Exact amount inserted
            result = new PaymentResult(PaymentStatus.SUCCESS, 0, 0,null);
        } else if (insertedAmount < amountRequired) {
            // Not enough money
            result = new PaymentResult(PaymentStatus.INSUFFICIENT_FUNDS, remain, 0,null);
        } else {
            // Extra money inserted → return change
            int change = insertedAmount - amountRequired;
            result = new PaymentResult(PaymentStatus.SUCCESS, 0, change,null);
        }
        return result;
    }

    /* ? cancel or refund */
    public Map<Denomination, Integer> cancel() {
        Map<Denomination, Integer> refund = new HashMap<>(insertedCash);

        insertedCash.clear();  // Clears the transaction state
        insertedAmount = 0;

        return refund; // Returns the customer's inserted denominations
    }

    //  transfer the customer's cash after successful transaction
    public Map<Denomination, Integer> getInsertedCash() {
        return new HashMap<>(insertedCash);
    }

    // commit the customer's cash into the machine only after successful cashOnly payment
    public Map<Denomination, Integer> completePayment() {

        Map<Denomination, Integer> cash = new HashMap<>(insertedCash);

        insertedCash.clear();
        insertedAmount = 0;

        return cash;
    }
}
