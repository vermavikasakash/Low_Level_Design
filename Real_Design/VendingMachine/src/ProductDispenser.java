import models.Product;

public class ProductDispenser {

    public boolean dispense(Product product) {
        System.out.println("Dispensing product: " + product.getName());
        return true;
    }
}