package model;

import lombok.Getter;

@Getter
public class User {

    private int userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String deviceToken;
    private NotificationPreference preference;

    public User(int userId, String name, String email, String phoneNumber, String deviceToken, NotificationPreference preference) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.deviceToken = deviceToken;
        this.preference = preference;
    }
}
