public class NotificationFactory {

    public Notification create(String type) {

        if (type.equals("EMAIL")) {
            return new EmailNotification();
        }

        if (type.equals("SMS")) {
            return new SMSNotification();
        }

        if (type.equals("PUSH")) {
            return new PushNotification();
        }

        throw new IllegalArgumentException(
                "Unsupported notification type: " + type
        );
    }
}