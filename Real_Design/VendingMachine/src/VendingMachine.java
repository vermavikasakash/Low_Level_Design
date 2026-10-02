import enums.Denomination;
import enums.PaymentStatus;
import inventory.CashInventory;
import inventory.Inventory;
import inventory.InventoryItem;
import models.Product;
import payments.CashPayment;
import payments.Payment;
import payments.PaymentResult;

import java.util.Map;

public class VendingMachine {

    private Inventory inventory;
    private ProductDispenser productDispenser;
    private CashInventory cashInventory;

    public VendingMachine(Inventory inventory, ProductDispenser productDispenser, CashInventory cashInventory) {
        this.inventory = inventory;
        this.productDispenser = productDispenser;
        this.cashInventory = cashInventory;
    }

    // methods
    public Product selectProduct(String slotId) {
        InventoryItem item = inventory.getItem(slotId);

        if (item.getQuantity() <= 0) {
            throw new IllegalStateException("Product is out of stock");
        }
        return item.getProduct();
    }

    // purchase
    public PaymentResult purchase(String slotId, Payment payment) {

        Product product = selectProduct(slotId);
        int price = (int) product.getPrice();

        PaymentResult result = payment.pay(price);

        if (result.getStatus() != PaymentStatus.SUCCESS) {
            return result;
        }

        // Calculate exact change once
        // Calculate exact change once
        if (result.getChange() > 0) {

            try {
                Map<Denomination, Integer> changeBreakdown = cashInventory.calculateChange(result.getChange());

                result.setChangeBreakdown(changeBreakdown);

            } catch (IllegalStateException e) {

                // Change cannot be made → cancel transaction
                if (payment instanceof CashPayment cashPayment) {
                    Map<Denomination, Integer> refund = cashPayment.cancel();

                    System.out.println("Refunding: " + refund);
                }

                return new PaymentResult(PaymentStatus.INSUFFICIENT_CHANGE, 0, 0, null);
            }
        }

        // 1. Dispense product
        boolean dispensed = productDispenser.dispense(product);

        if (!dispensed) {

            if (payment instanceof CashPayment cashPayment) {
                Map<Denomination, Integer> refund = cashPayment.cancel();

                System.out.println("Refunding: " + refund);
            }

            return new PaymentResult(PaymentStatus.PAYMENT_FAILED, 0, 0, null);
        }

        // 2. Decrease inventory
        inventory.decreaseQuantity(slotId, 1);

        // 3. Commit customer's cash
        if (payment instanceof CashPayment cashPayment) {

            Map<Denomination, Integer> customerCash = cashPayment.completePayment();

            cashInventory.addCash(customerCash);
        }

        // 4. Dispense exact change
        if (result.getChange() > 0) {
            cashInventory.dispenseChange(result.getChangeBreakdown());
        }

        return result;
    }
}