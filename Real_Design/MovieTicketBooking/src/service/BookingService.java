package service;

import enums.BookingStatus;
import enums.SeatStatus;
import model.Booking;
import model.BookingSeat;
import model.Show;
import model.ShowSeat;
import model.ShowSeatLockManager;
import model.User;
import payment.PaymentService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BookingService {

    private static final long LOCK_DURATION_MILLIS = 5 * 60 * 1000;
    private final PaymentService paymentService;
    private final ShowSeatLockManager lockManager;
    private int bookingIdCounter = 1;
    private int bookingSeatIdCounter = 1;

    public BookingService(PaymentService paymentService, ShowSeatLockManager lockManager) {
        this.paymentService = paymentService;
        this.lockManager = lockManager;
    }

    // =========================================================
    // 1. LOCK SEATS
    // =========================================================

    public void lockSeats(User user, Show show, List<ShowSeat> showSeats) {

        // Sort seats by ID to avoid deadlock
        List<ShowSeat> sortedSeats = showSeats.stream().sorted(Comparator.comparingInt(ShowSeat::getShowSeatId)).toList();

        List<ReentrantLock> acquiredLocks = new ArrayList<>();

        try {

            // -------------------------------------------------
            // Acquire Java concurrency locks
            // -------------------------------------------------

            for (ShowSeat showSeat : sortedSeats) {

                ReentrantLock lock = lockManager.getLock(showSeat.getShowSeatId());

                lock.lock();

                acquiredLocks.add(lock);
            }

            // -------------------------------------------------
            // Validate ALL seats first
            // -------------------------------------------------

            for (ShowSeat showSeat : sortedSeats) {

                // Seat must belong to requested show
                if (showSeat.getShow().getShowId() != show.getShowId()) {

                    throw new RuntimeException("Seat does not belong to this show");
                }

                // Seat must be available
                if (!isAvailable(showSeat)) {

                    throw new RuntimeException("Seat is not available: " + showSeat.getSeat().getSeatNumber());
                }
            }

            // -------------------------------------------------
            // Lock ALL seats
            // -------------------------------------------------

            Timestamp expiry = new Timestamp(System.currentTimeMillis() + LOCK_DURATION_MILLIS);

            for (ShowSeat showSeat : sortedSeats) {

                showSeat.setStatus(SeatStatus.LOCKED);

                showSeat.setLockedBy(user);

                showSeat.setLockExpiry(expiry);
            }

        } finally {

            // -------------------------------------------------
            // Always release Java locks
            // -------------------------------------------------

            for (int i = acquiredLocks.size() - 1; i >= 0; i--) {

                acquiredLocks.get(i).unlock();
            }
        }
    }


    // =========================================================
    // 2. CONFIRM BOOKING
    // =========================================================

    public Booking confirmBooking(User user, Show show, List<ShowSeat> showSeats) {

        // Sort seats by ID to avoid deadlock
        List<ShowSeat> sortedSeats = showSeats.stream().sorted(Comparator.comparingInt(ShowSeat::getShowSeatId)).toList();

        List<ReentrantLock> acquiredLocks = new ArrayList<>();

        try {

            // -------------------------------------------------
            // Acquire Java concurrency locks
            // -------------------------------------------------

            for (ShowSeat showSeat : sortedSeats) {

                ReentrantLock lock = lockManager.getLock(showSeat.getShowSeatId());

                lock.lock();

                acquiredLocks.add(lock);
            }

            // -------------------------------------------------
            // Validate ALL seats
            // -------------------------------------------------

            for (ShowSeat showSeat : sortedSeats) {

                // 1. Seat belongs to this show
                if (showSeat.getShow().getShowId() != show.getShowId()) {

                    throw new RuntimeException("Seat does not belong to this show");
                }

                // 2. Seat must be LOCKED
                if (showSeat.getStatus() != SeatStatus.LOCKED) {

                    throw new RuntimeException("Seat is not locked");
                }

                // 3. Seat must be locked by this user
                if (showSeat.getLockedBy() == null || showSeat.getLockedBy().getUserId() != user.getUserId()) {

                    throw new RuntimeException("Seat is locked by another user");
                }

                // 4. Lock must not be expired
                if (showSeat.getLockExpiry() == null || showSeat.getLockExpiry().before(new Timestamp(System.currentTimeMillis()))) {

                    showSeat.setStatus(SeatStatus.AVAILABLE);

                    showSeat.setLockedBy(null);

                    showSeat.setLockExpiry(null);

                    throw new RuntimeException("Seat lock has expired");
                }
            }

            // -------------------------------------------------
            // Calculate total amount
            // -------------------------------------------------

            BigDecimal totalAmount = sortedSeats.stream().map(showSeat -> showSeat.getSeat().getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);

            // -------------------------------------------------
            // Process payment
            // -------------------------------------------------

            boolean paymentSuccessful = paymentService.processPayment(user, totalAmount);

            // -------------------------------------------------
            // Payment failed
            // -------------------------------------------------

            if (!paymentSuccessful) {

                for (ShowSeat showSeat : sortedSeats) {

                    showSeat.setStatus(SeatStatus.AVAILABLE);

                    showSeat.setLockedBy(null);

                    showSeat.setLockExpiry(null);
                }

                throw new RuntimeException("Payment failed");
            }

            // -------------------------------------------------
            // Payment successful
            // LOCKED -> BOOKED
            // -------------------------------------------------

            List<BookingSeat> bookingSeats = new ArrayList<>();

            for (ShowSeat showSeat : sortedSeats) {

                showSeat.setStatus(SeatStatus.BOOKED);

                showSeat.setLockedBy(null);

                showSeat.setLockExpiry(null);

                BookingSeat bookingSeat = new BookingSeat(bookingSeatIdCounter++, showSeat, showSeat.getSeat().getPrice());

                bookingSeats.add(bookingSeat);
            }

            // -------------------------------------------------
            // Create Booking
            // -------------------------------------------------

            Booking booking = new Booking(bookingIdCounter++, user, show, bookingSeats, totalAmount, BookingStatus.CONFIRMED);

            return booking;

        } finally {

            // -------------------------------------------------
            // Always release Java locks
            // -------------------------------------------------

            for (int i = acquiredLocks.size() - 1; i >= 0; i--) {

                acquiredLocks.get(i).unlock();
            }
        }
    }


    // =========================================================
    // HELPER: CHECK WHETHER SEAT IS AVAILABLE
    // =========================================================

    private boolean isAvailable(ShowSeat showSeat) {

        // Completely free
        if (showSeat.getStatus() == SeatStatus.AVAILABLE) {

            return true;
        }

        // Locked but lock has expired
        if (showSeat.getStatus() == SeatStatus.LOCKED && showSeat.getLockExpiry() != null && showSeat.getLockExpiry().before(new Timestamp(System.currentTimeMillis()))) {

            // Clean expired lock
            showSeat.setStatus(SeatStatus.AVAILABLE);

            showSeat.setLockedBy(null);

            showSeat.setLockExpiry(null);

            return true;
        }

        return false;
    }

    // =========================================================
    // 3. CANCEL BOOKING
    // =========================================================
    public void cancelBooking(User user, Booking booking) {

        // -------------------------------------------------
        // 1. Validate booking ownership
        // -------------------------------------------------

        if (booking.getUser().getUserId() != user.getUserId()) {

            throw new RuntimeException("User does not own this booking");
        }

        // -------------------------------------------------
        // 2. Booking must be CONFIRMED
        // -------------------------------------------------

        if (booking.getStatus() != BookingStatus.CONFIRMED) {

            throw new RuntimeException("Booking cannot be cancelled");
        }

        // -------------------------------------------------
        // Get all ShowSeats from BookingSeats
        // -------------------------------------------------

        List<ShowSeat> showSeats = booking.getSeats().stream().map(BookingSeat::getShowSeat).sorted(Comparator.comparingInt(ShowSeat::getShowSeatId)).toList();

        List<ReentrantLock> acquiredLocks = new ArrayList<>();

        try {

            // -------------------------------------------------
            // 3. Acquire Java concurrency locks
            // -------------------------------------------------

            for (ShowSeat showSeat : showSeats) {

                ReentrantLock lock = lockManager.getLock(showSeat.getShowSeatId());

                lock.lock();

                acquiredLocks.add(lock);
            }

            // -------------------------------------------------
            // 4. Validate seats
            // -------------------------------------------------

            for (ShowSeat showSeat : showSeats) {

                if (showSeat.getStatus() != SeatStatus.BOOKED) {

                    throw new RuntimeException("Seat is not currently booked");
                }
            }

            // -------------------------------------------------
            // 5. Release seats
            // BOOKED -> AVAILABLE
            // -------------------------------------------------

            for (ShowSeat showSeat : showSeats) {

                showSeat.setStatus(SeatStatus.AVAILABLE);

                showSeat.setLockedBy(null);

                showSeat.setLockExpiry(null);
            }

            // -------------------------------------------------
            // 6. Cancel booking
            // CONFIRMED -> CANCELLED
            // -------------------------------------------------

            booking.setStatus(BookingStatus.CANCELLED);

        } finally {

            // -------------------------------------------------
            // 7. Always release Java locks
            // -------------------------------------------------

            for (int i = acquiredLocks.size() - 1; i >= 0; i--) {

                acquiredLocks.get(i).unlock();
            }
        }
    }
}