package inventory;
import lombok.ToString;
import models.Product;
import java.util.concurrent.ConcurrentHashMap;
@ToString
public class Inventory {

    private final ConcurrentHashMap<String, InventoryItem> items;

    public Inventory() {
        items = new ConcurrentHashMap<>();
    }

    public void addProduct(String slotId, Product product, int quantity) {
        InventoryItem item = items.get(slotId);

        if (item == null) {
            InventoryItem newInventoryItem = new InventoryItem(product, quantity);
            // Slot is empty
            items.put(slotId, newInventoryItem);
        } else {
            // Slot already has a product
            if (item.getProduct().getProductId() != product.getProductId()) {
                throw new IllegalStateException("Slot already contains a different product");
            }

            item.addQuantity(quantity);
        }
    }

    public void decreaseQuantity(String slotId, int quantity) {
        InventoryItem item = items.get(slotId);

        item.decreaseQuantity(quantity);
    }

    public InventoryItem getItem(String slotId) {
        InventoryItem item = items.get(slotId);

        if (item == null) {
            throw new IllegalStateException("This slot is not present");
        }

        return item;
    }
}
