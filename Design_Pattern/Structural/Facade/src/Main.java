public class Main {

    public static void main(String[] args) {

        InventoryService inventory = new InventoryService();
        PaymentService payment = new PaymentService();
        OrderService order = new OrderService();
        NotificationService notification = new NotificationService();

        OrderFacade orderSystem = new OrderFacade(inventory, payment, order, notification);

        orderSystem.placeOrder(101, 5000);
    }
}