### Requirement Collection

1. Movies — multiple movies.
2. Theaters — multiple theaters.
3. Screens — each theater has one or more screens.
4. Shows — a movie is scheduled on a screen at a particular time.
5. Seats — each screen has multiple seats.
6. model.Seat availability — users can see/select available seats for a show.
7. model.Booking — user can book one or more seats for a show.
8. Pricing — seats have prices/types and booking calculates the total.
9. Cancellation — cancel booking and make seats available again.
10. Payment — simple payment abstraction; no real gateway.
11. Two users must not be able to book the same seat for the same show.
    - When a user selects seats, the seats should be temporarily locked.
    - Another user cannot select a seat while it is locked.
    - A user can only complete the booking for seats that they currently hold.
### Classes and Entities
Requirement                     → Design
---------------------------------------------------------
Users/preferences               → model.User
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