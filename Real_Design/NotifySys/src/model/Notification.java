package model;

import enums.NotificationChannel;
import enums.NotificationPriority;
import enums.NotificationStatus;
import enums.NotificationType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Notification {

    private NotificationType type;
    private NotificationPriority priority;
    private int userId;
    private String message;
    private NotificationStatus status;
    private List<NotificationChannel> channels;
    private int retryCount;
    private List<NotificationChannel> pendingChannels;

    @Setter
    private long sequenceNumber;

    public Notification(NotificationType type, NotificationPriority priority, int userId, String message, List<NotificationChannel> channels) {
        this.type = type;
        this.priority = priority;
        this.userId = userId;
        this.message = message;
        this.channels = channels;
        this.pendingChannels = new ArrayList<>(channels);
        this.status = NotificationStatus.PENDING;
        this.retryCount = 0;
    }
    // methods
    public void markAsSent() {
        this.status = NotificationStatus.SENT;
    }

    public void markAsRetrying() {
        this.status = NotificationStatus.RETRYING;
    }

    public void markAsFailed() {
        this.status = NotificationStatus.FAILED;
    }
    public void incrementRetryCount() {
        this.retryCount++;
    }
    public List<NotificationChannel> getPendingChannels() {
        return pendingChannels;
    }
}
