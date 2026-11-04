package model;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@ToString
public class Screen {

    private final int screenId;
    private final String screenName;
    private final List<Seat> seats = new ArrayList<>();

    public Screen(int screenId, String screenName) {
        this.screenId = screenId;
        this.screenName = screenName;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public int getScreenId() {
        return screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}