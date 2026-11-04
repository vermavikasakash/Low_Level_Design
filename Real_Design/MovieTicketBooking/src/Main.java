import enums.SeatType;
import model.Booking;
import model.Movie;
import model.Screen;
import model.Seat;
import model.Show;
import model.ShowSeat;
import model.ShowSeatLockManager;
import model.User;
import payment.MockPaymentService;
import payment.PaymentService;
import service.BookingService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // =====================================================
        // 1. Create Movie
        // =====================================================

        Movie movie = new Movie(1, "Interstellar");


        // =====================================================
        // 2. Create Screen
        // =====================================================

        Screen screen = new Screen(1, "Screen 1");


        // =====================================================
        // 3. Create Physical Seats
        // =====================================================

        Seat seat1 = new Seat(1, "A1", SeatType.NORMAL, new BigDecimal("200"));

        Seat seat2 = new Seat(2, "A2", SeatType.PREMIUM, new BigDecimal("300"));

        Seat seat3 = new Seat(3, "A3", SeatType.RECLINER, new BigDecimal("500"));


        // Add seats to screen

        screen.addSeat(seat1);
        screen.addSeat(seat2);
        screen.addSeat(seat3);


        // =====================================================
        // 4. Create Show
        // =====================================================

        Show show = new Show(1, movie, screen, new Timestamp(System.currentTimeMillis()));


        // =====================================================
        // 5. Create Show-Specific Seats
        // =====================================================

        ShowSeat showSeat1 = new ShowSeat(1, show, seat1);

        ShowSeat showSeat2 = new ShowSeat(2, show, seat2);

        ShowSeat showSeat3 = new ShowSeat(3, show, seat3);


        // =====================================================
        // 6. Create User
        // =====================================================

        User user = new User(1, "vikas@example.com");


        // =====================================================
        // 7. Create Payment Service
        // =====================================================

        PaymentService paymentService = new MockPaymentService();


        // =====================================================
        // 8. Create Lock Manager
        // =====================================================

        ShowSeatLockManager lockManager = new ShowSeatLockManager();


        // =====================================================
        // 9. Create Booking Service
        // =====================================================

        BookingService bookingService = new BookingService(paymentService, lockManager);


        // =====================================================
        // 10. User Selects Seats
        // =====================================================

        List<ShowSeat> selectedSeats = List.of(showSeat1, showSeat2);


        System.out.println("======================================");

        System.out.println("BEFORE LOCKING");

        System.out.println("======================================");

        System.out.println("A1: " + showSeat1.getStatus());

        System.out.println("A2: " + showSeat2.getStatus());


        // =====================================================
        // 11. Lock Seats
        // =====================================================

        bookingService.lockSeats(user, show, selectedSeats);


        System.out.println("\n======================================");

        System.out.println("AFTER LOCKING");

        System.out.println("======================================");

        System.out.println("A1: " + showSeat1.getStatus());

        System.out.println("A2: " + showSeat2.getStatus());

        System.out.println("Locked by: " + showSeat1.getLockedBy().getEmail());

        System.out.println("Lock expiry: " + showSeat1.getLockExpiry());


        // =====================================================
        // 12. Confirm Booking
        // =====================================================

        Booking booking = bookingService.confirmBooking(user, show, selectedSeats);


        System.out.println("\n======================================");

        System.out.println("AFTER BOOKING");

        System.out.println("======================================");

        System.out.println("Booking ID: " + booking.getBookingId());

        System.out.println("User: " + booking.getUser().getEmail());

        System.out.println("Movie: " + booking.getShow().getMovie().getMovieName());

        System.out.println("Total amount: ₹" + booking.getTotalAmount());

        System.out.println("Booking status: " + booking.getStatus());

        System.out.println("A1: " + showSeat1.getStatus());

        System.out.println("A2: " + showSeat2.getStatus());


        // =====================================================
        // 13. Cancel Booking
        // =====================================================

        bookingService.cancelBooking(user, booking);


        System.out.println("\n======================================");

        System.out.println("AFTER CANCELLATION");

        System.out.println("======================================");

        System.out.println("Booking status: " + booking.getStatus());

        System.out.println("A1: " + showSeat1.getStatus());

        System.out.println("A2: " + showSeat2.getStatus());
    }
}