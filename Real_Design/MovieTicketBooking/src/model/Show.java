package model;

import lombok.ToString;

import java.sql.Timestamp;
@ToString
public class Show {

    private final int showId;
    private final Movie movie;
    private final Screen screen;
    private final Timestamp time;

    public Show(
            int showId,
            Movie movie,
            Screen screen,
            Timestamp time
    ) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.time = time;
    }

    public int getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public Timestamp getTime() {
        return time;
    }
}