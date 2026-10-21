package service;

import enums.NotificationChannel;
import enums.NotificationPriority;
import enums.NotificationType;
import model.Notification;
import model.User;
import queue.NotificationQueue;

import java.util.List;

public class NotificationService {

    private NotificationQueue queue;

    public NotificationService(NotificationQueue queue) {
        this.queue = queue;
    }

    public void sendNotification(User user, NotificationType type, NotificationPriority priority, String message) {

        List<NotificationChannel> channels = user.getPreference().getEnabledChannels();

        Notification notification = new Notification(type, priority, user.getUserId(), message, channels);

        queue.add(notification);
    }
}
