public class OrderSystem {

    private InventoryService inventoryService;
    private PaymentService paymentService;
    private OrderService orderService;
    private NotificationService notificationService;

    public OrderSystem(InventoryService inventoryService, PaymentService paymentService, OrderService orderService, NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.notificationService = notificationService;
    }

    public void placeOrder(int productId, double amount) {

        if (inventoryService.checkStock(productId)) {

            if (paymentService.makePayment(amount)) {

                orderService.createOrder(productId);
                notificationService.sendConfirmation();
            }
        }
    }

}