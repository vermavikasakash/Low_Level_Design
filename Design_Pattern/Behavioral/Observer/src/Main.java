public class Main {

    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        Subscriber vikas = new EmailSubscriber();
        Subscriber rahul = new MobileSubscriber();

        channel.subscribe(vikas);
        channel.subscribe(rahul);

        channel.uploadVideo("Java Observer Pattern");
        // Both get notification

        channel.unsubscribe(vikas);

        channel.uploadVideo("State Pattern");
        // Only Rahul gets notification
    }
}