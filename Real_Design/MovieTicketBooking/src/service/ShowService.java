package service;

import model.Show;
import model.ShowSeat;

import java.util.ArrayList;
import java.util.List;

public class ShowService {

    private final List<Show> shows = new ArrayList<>();

    private final List<ShowSeat> showSeats = new ArrayList<>();

    public void addShow(Show show) {
        shows.add(show);
    }

    public void addShowSeat(ShowSeat showSeat) {
        showSeats.add(showSeat);
    }

    public List<Show> getShows() {
        return shows;
    }

    public List<ShowSeat> getAvailableSeats(Show show) {

        List<ShowSeat> availableSeats = new ArrayList<>();

        for (ShowSeat showSeat : showSeats) {

            if (showSeat.getShow().getShowId() == show.getShowId()) {

                if (showSeat.getStatus()
                        == enums.SeatStatus.AVAILABLE) {

                    availableSeats.add(showSeat);
                }
            }
        }

        return availableSeats;
    }
}