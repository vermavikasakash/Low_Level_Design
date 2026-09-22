public class EmailNotification implements Notification {

    @Override
    public void send(String recipient, String message) {

        System.out.println("Sending email to: " + recipient);

        System.out.println("Email message: " + message);
    }
}