public class EmailSubscriber implements Subscriber {

    @Override
    public void update(String video) {
        System.out.println("Email notification: " + video);
    }
}