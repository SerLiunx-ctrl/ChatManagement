package me.serliunx.chatmanagement.utils;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class TimeUtils {
    public static String formatDuration(String format, Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return format.replace("%hours%", String.valueOf(hours))
                .replace("%minutes%", String.valueOf(minutes))
                .replace("%seconds%", String.valueOf(seconds));
    }

    public static String replaceHolders(@NotNull String rawText, @NotNull Player player){
        return rawText;
    }
}
