package service;


import config.NotificationConfig;
import enums.NotificationChannel;
import factory.NotificationSenderFactory;
import model.Notification;
import queue.NotificationQueue;
import sender.NotificationSender;

import java.util.Iterator;

public class NotificationProcessor {

    private NotificationQueue queue;
    private NotificationSenderFactory senderFactory;
    private NotificationConfig config;

    public NotificationProcessor(NotificationQueue queue, NotificationSenderFactory senderFactory, NotificationConfig config) {
        this.queue = queue;
        this.senderFactory = senderFactory;
        this.config = config;
    }

    public void processNext() {

        Notification notification = queue.poll();

        if (notification == null) {
            return;
        }

        Iterator<NotificationChannel> iterator = notification.getPendingChannels().iterator();

        while (iterator.hasNext()) {

            NotificationChannel channel = iterator.next();

            NotificationSender sender = senderFactory.getSender(channel);

            boolean success = sender.send(notification);

            if (success) {
                iterator.remove();
                continue;
            }

            if (notification.getRetryCount() < config.getMaxRetries()) {

                notification.incrementRetryCount();

                notification.markAsRetrying();

                queue.add(notification);

            } else {

                notification.markAsFailed();
            }

            return;
        }

        notification.markAsSent();
    }
}
