package model;

import enums.SeatStatus;

import java.sql.Timestamp;

public class ShowSeat {

    private final int showSeatId;
    private final Show show;
    private final Seat seat;

    private SeatStatus status;
    private User lockedBy;
    private Timestamp lockExpiry;

    public ShowSeat(int showSeatId, Show show, Seat seat) {
        this.showSeatId = showSeatId;
        this.show = show;
        this.seat = seat;
        this.status = SeatStatus.AVAILABLE;
    }

    public int getShowSeatId() {
        return showSeatId;
    }

    public Show getShow() {
        return show;
    }

    public Seat getSeat() {
        return seat;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public User getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(User lockedBy) {
        this.lockedBy = lockedBy;
    }

    public Timestamp getLockExpiry() {
        return lockExpiry;
    }

    public void setLockExpiry(Timestamp lockExpiry) {
        this.lockExpiry = lockExpiry;
    }
}