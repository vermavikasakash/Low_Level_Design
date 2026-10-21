package config;

public class NotificationConfig {

    private int maxRetries;

    public NotificationConfig(int maxRetries) {
        this.maxRetries = maxRetries;
    }
    public int getMaxRetries() {
        return maxRetries;
    }
}
