import config.NotificationConfig;
import enums.NotificationChannel;
import enums.NotificationPriority;
import enums.NotificationType;
import factory.NotificationSenderFactory;
import model.NotificationPreference;
import model.User;
import queue.NotificationQueue;
import service.NotificationProcessor;
import service.NotificationService;

public class Main {

    public static void main(String[] args) {

        // -----------------------------
        // 1. Create user preferences
        // -----------------------------

        NotificationPreference preference = new NotificationPreference();

        preference.setChannelPreference(NotificationChannel.EMAIL, true);

        preference.setChannelPreference(NotificationChannel.SMS, true);

        preference.setChannelPreference(NotificationChannel.PUSH, false);

        System.out.println("user preferences : " + preference.getEnabledChannels());


        // -----------------------------
        // 2. Create User
        // -----------------------------

        User user = new User(101, "Vikas", "vikas@example.com", "9876543210", "device-token", preference);


        // -----------------------------
        // 3. Create Queue
        // -----------------------------

        NotificationQueue queue = new NotificationQueue();


        // -----------------------------
        // 4. Create NotificationService
        // -----------------------------

        NotificationService notificationService = new NotificationService(queue);


        // -----------------------------
        // 5. Create Sender Factory
        // -----------------------------

        NotificationSenderFactory senderFactory = new NotificationSenderFactory();


        // -----------------------------
        // 6. Create Retry Configuration
        // -----------------------------

        NotificationConfig config = new NotificationConfig(3);


        // -----------------------------
        // 7. Create Processor
        // -----------------------------

        NotificationProcessor processor = new NotificationProcessor(queue, senderFactory, config);


        // -----------------------------
        // 8. Create Notification
        // -----------------------------

        notificationService.sendNotification(user, NotificationType.PAYMENT, NotificationPriority.HIGH, "Payment successful");


        // -----------------------------
        // 9. Process Notification
        // -----------------------------

        processor.processNext();


        // -----------------------------
        // 10. Process retries
        // -----------------------------

        processor.processNext();
        processor.processNext();
        processor.processNext();


        // -----------------------------
        // 11. Check final state
        // -----------------------------

        // If you want to print the notification,
        // keep a reference to it separately.
    }
}