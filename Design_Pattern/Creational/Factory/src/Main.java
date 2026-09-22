public class Main {

    public static void main(String[] args) {

        NotificationFactory factory = new NotificationFactory();

        NotificationService service = new NotificationService(factory);

        service.send("EMAIL", "vikas@example.com", "Your order has been shipped");
    }
}