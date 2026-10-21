package enums;

public enum NotificationPriority {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int value;

    NotificationPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
