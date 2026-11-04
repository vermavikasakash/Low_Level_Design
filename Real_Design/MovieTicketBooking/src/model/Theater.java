package model;

import java.util.HashMap;
import java.util.Map;

public class Theater {

    private final int theaterId;
    private final String theaterName;

    private final Map<Integer, Screen> screens = new HashMap<>();

    public Theater(int theaterId, String theaterName) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
    }

    public void addScreen(Screen screen) {
        screens.put(screen.getScreenId(), screen);
    }

    public int getTheaterId() {
        return theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public Map<Integer, Screen> getScreens() {
        return screens;
    }
}