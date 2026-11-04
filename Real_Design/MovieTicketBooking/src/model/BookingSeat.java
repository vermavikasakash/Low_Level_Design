package model;

import java.math.BigDecimal;

public class BookingSeat {

    private final int bookingSeatId;
    private final ShowSeat showSeat;
    private final BigDecimal price;

    public BookingSeat(
            int bookingSeatId,
            ShowSeat showSeat,
            BigDecimal price
    ) {
        this.bookingSeatId = bookingSeatId;
        this.showSeat = showSeat;
        this.price = price;
    }

    public int getBookingSeatId() {
        return bookingSeatId;
    }

    public ShowSeat getShowSeat() {
        return showSeat;
    }

    public BigDecimal getPrice() {
        return price;
    }
}