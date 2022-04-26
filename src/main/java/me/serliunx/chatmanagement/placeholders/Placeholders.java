package me.serliunx.chatmanagement.placeholders;

import me.serliunx.chatmanagement.ChatManagement;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class Placeholders {

    public static String replacePlaceHolders(String rawText, Player player){
        if(ChatManagement.getInstance().isUsePapi())
            return PlaceholderAPI.setPlaceholders(player, rawText);
        else
            return rawText;
    }

    @Nullable
    public String getPlaceHolder(OfflinePlayer player, String param){
        return switch (param) {
            case "player_prefix" -> ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId()).getPrefix();
            case "player_suffix" -> ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId()).getSuffix();
            case "player_prefix_holo" -> String.join("\n", ChatManagement.getInstance().getUserManager()
                    .getUser(player.getUniqueId()).getPrefixHolo());
            case "player_suffix_holo" -> String.join("\n", ChatManagement.getInstance().getUserManager()
                    .getUser(player.getUniqueId()).getSuffixHolo());
            case "player_text_holo" -> String.join("\n", ChatManagement.getInstance().getUserManager()
                    .getUser(player.getUniqueId()).getTextHolo());
            case "player_pm_status" -> String.valueOf(ChatManagement.getInstance().getUserManager()
                    .getUser(player.getUniqueId()).isPmStatus());
            default -> null;
        };
    }
}
