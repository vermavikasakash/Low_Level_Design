package queue;

import model.Notification;

import java.util.Comparator;
import java.util.PriorityQueue;

public class NotificationQueue {

    private long sequence = 0;

    private PriorityQueue<Notification> queue;

    public NotificationQueue() {

        Comparator<Notification> comparator = (n1, n2) -> {

            int priorityCompare = Integer.compare(n2.getPriority().getValue(), n1.getPriority().getValue());

            if (priorityCompare != 0) {
                return priorityCompare;
            }

            return Long.compare(n1.getSequenceNumber(), n2.getSequenceNumber());
        };

        queue = new PriorityQueue<>(comparator);
    }

    public void add(Notification notification) {

        notification.setSequenceNumber(sequence++);

        queue.offer(notification);
    }

    public Notification poll() {

        return queue.poll();
    }
}
