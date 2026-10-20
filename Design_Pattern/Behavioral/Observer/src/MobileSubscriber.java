public class MobileSubscriber implements Subscriber {

    @Override
    public void update(String video) {
        System.out.println("Mobile notification: " + video);
    }
}