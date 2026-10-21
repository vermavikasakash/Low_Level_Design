package factory;

import enums.NotificationChannel;
import sender.EmailSender;
import sender.NotificationSender;
import sender.PushSender;
import sender.SmsSender;

import java.util.HashMap;
import java.util.Map;

public class NotificationSenderFactory {

    private Map<NotificationChannel, NotificationSender> senders;

    public NotificationSenderFactory() {

        senders = new HashMap<>();

        senders.put(NotificationChannel.EMAIL, new EmailSender());

        senders.put(NotificationChannel.SMS, new SmsSender());

        senders.put(NotificationChannel.PUSH, new PushSender());
    }

    public NotificationSender getSender(NotificationChannel channel) {
        return senders.get(channel);
    }
}