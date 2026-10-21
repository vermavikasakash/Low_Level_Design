package sender;


import model.Notification;

public class PushSender implements NotificationSender {

    @Override
    public boolean send(Notification notification) {
        System.out.println("push sent : " + notification);
        return false;
    }
}
