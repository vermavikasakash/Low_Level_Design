package model;

import enums.BookingStatus;

import java.math.BigDecimal;
import java.util.List;

public class Booking {

    private int bookingId;
    private User user;
    private Show show;
    private List<BookingSeat> seats;
    private BigDecimal totalAmount;
    private BookingStatus status;

    public Booking() {
    }

    public Booking(
            int bookingId,
            User user,
            Show show,
            List<BookingSeat> seats,
            BigDecimal totalAmount,
            BookingStatus status
    ) {
        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<BookingSeat> getSeats() {
        return seats;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }
    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}