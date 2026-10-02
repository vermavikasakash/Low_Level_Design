import enums.Denomination;
import inventory.CashInventory;
import inventory.Inventory;
import models.Product;
import payments.CashPayment;
import payments.PaymentResult;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Product chips = new Product(20, 1, "Lays");

        inventory.addProduct("A1", chips, 5);

        CashInventory cashInventory = new CashInventory();
        System.out.println("cashInventory : " + cashInventory);

        CashPayment cashPayment = new CashPayment();
        cashPayment.insertCash(Denomination.TWENTY);

        ProductDispenser productDispenser = new ProductDispenser();
       VendingMachine vendingMachine = new VendingMachine(inventory,productDispenser,cashInventory);

       PaymentResult result = vendingMachine.purchase("A1", cashPayment);

        System.out.println(result.getStatus());

        System.out.println("cashInventory after : " + cashInventory);
        System.out.println("inventory after : " + inventory);

    }
}