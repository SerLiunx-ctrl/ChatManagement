package me.serliunx.chatmanagement.placeholders;

import me.serliunx.chatmanagement.ChatManagement;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class Placeholders {
    public static String replacePlaceHolders(String rawText, Player player){
        if(ChatManagement.getInstance().isUsePapi())
            return PlaceholderAPI.setPlaceholders(player, rawText);
        else
            return rawText;
    }
}
