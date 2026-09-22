public class NotificationService {

    private NotificationFactory factory;

    public NotificationService(NotificationFactory factory) {
        this.factory = factory;
    }

    public void send(String type, String recipient, String message) {

        Notification notification = factory.create(type);

        notification.send(recipient, message);
    }
}