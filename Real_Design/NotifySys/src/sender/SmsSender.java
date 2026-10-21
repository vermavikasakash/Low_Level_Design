package sender;


import model.Notification;

public class SmsSender implements NotificationSender {

    @Override
    public boolean send(Notification notification) {

        System.out.println("sms sending failed" );
        return false;
    }
}