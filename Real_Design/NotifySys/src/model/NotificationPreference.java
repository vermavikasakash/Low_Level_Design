package model;

import enums.NotificationChannel;
import enums.NotificationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationPreference {

    private Map<NotificationChannel, Boolean> channelPreferences;
    private Map<NotificationType, Integer> dailyLimits;

    public NotificationPreference() {
        this.channelPreferences = new HashMap<>();
        this.dailyLimits = new HashMap<>();
    }

    public void setChannelPreference(NotificationChannel channel, boolean enabled) {
        channelPreferences.put(channel, enabled);
    }

    public boolean isChannelEnabled(NotificationChannel channel) {
        return channelPreferences.getOrDefault(channel, false);
    }

    public List<NotificationChannel> getEnabledChannels() {
        return channelPreferences.entrySet().stream().filter(entry -> entry.getValue()).map(Map.Entry::getKey).toList();
    }

    public void setDailyLimit(NotificationType type, int limit) {
        dailyLimits.put(type, limit);
    }

    public int getDailyLimit(NotificationType type) {
        return dailyLimits.getOrDefault(type, Integer.MAX_VALUE);
    }
}
