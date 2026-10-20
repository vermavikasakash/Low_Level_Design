import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel {

    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void uploadVideo(String video) {
        System.out.println("Uploaded: " + video);

        for (Subscriber subscriber : subscribers) {
            subscriber.update(video);
        }
    }
}