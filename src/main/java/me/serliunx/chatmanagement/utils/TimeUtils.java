package me.serliunx.chatmanagement.utils;

import java.time.Duration;

public class TimeUtils {
    public static String formatDuration(String format, Duration duration) {
        double hours = duration.toHours();
        double minutes = duration.toMinutes() / 60.0;
        double seconds = duration.toMillis() / 1000.0;

        return format.replace("{hours}", String.valueOf(hours))
                .replace("{minutes}", String.format("%.1f",minutes))
                .replace("{seconds}", String.format("%.1f",seconds));
    }
}
