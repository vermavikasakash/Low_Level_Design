package inventory;

import lombok.Getter;
import lombok.ToString;
import models.Product;

@ToString
public class InventoryItem {

    @Getter
    private final Product product;
    @Getter
    private int quantity;

    public InventoryItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity(int quantity) {
        if (this.quantity - quantity < 0) {
            throw new IllegalStateException("Quantity can't be less than zero");
        }
        this.quantity -= quantity;
    }

}