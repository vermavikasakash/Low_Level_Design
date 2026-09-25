public class Main {

    public static void main(String[] args) {

        InventoryService inventory = new InventoryService();
        PaymentService payment = new PaymentService();
        OrderService order = new OrderService();
        NotificationService notification = new NotificationService();

        OrderSystem orderSystem = new OrderSystem(inventory, payment, order, notification);

        orderSystem.placeOrder(101, 5000);
    }
}