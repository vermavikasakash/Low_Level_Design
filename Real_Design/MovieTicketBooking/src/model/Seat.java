package model;

import enums.SeatType;
import lombok.ToString;

import java.math.BigDecimal;
@ToString
public class Seat {

    private final int seatId;
    private final String seatNumber;
    private final SeatType type;
    private final BigDecimal price;

    public Seat(
            int seatId,
            String seatNumber,
            SeatType type,
            BigDecimal price
    ) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.type = type;
        this.price = price;
    }

    public int getSeatId() {
        return seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }
}