package sender;


import model.Notification;

public class EmailSender implements NotificationSender {

    @Override
    public boolean send(Notification notification) {

        System.out.println("email sent : " + notification);
        return true;
    }
}