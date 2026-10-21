### Requirement Collection

1. Send notifications to users.

2. Support multiple notification channels: - Email,- SMS, - Push

3. Notifications have priorities — HIGH, MEDIUM, LOW.

4. Users can configure notification preferences, including which channels are enabled.

5. Pending notifications are maintained in a priority-based queue.
- Higher-priority notifications are processed first.
- Notifications with the same priority are processed in FIFO order.

6. The system should maintain the status of each notification:
- PENDING
- RETRYING
- SENT
- FAILED

7. A notification should be sent through all channels enabled by the user's notification preferences.

8. If notification delivery fails, the system should retry it up to a configurable maximum retry count.

9. If the maximum retry count is exhausted, the notification should be marked as FAILED.

What we have : we have polymorphism, queue/priority, state, user preferences, retry behavior, and multiple external channels.

### Classes and Entities
Requirement                     → Design
---------------------------------------------------------
Users/preferences               → User
Channel preferences             → NotificationPreference
Notification data               → Notification
Priority                        → NotificationPriority
Priority + FIFO queue           → NotificationQueue
Channel sending                 → NotificationSender
Email/SMS/Push                  → Sender implementations
Sender selection                → NotificationSenderFactory
Create + queue notification     → NotificationService
Process queued notification     → NotificationProcessor
Maximum retry count             → NotificationConfig