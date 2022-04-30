package me.serliunx.chatmanagement.placeholder;

import me.serliunx.chatmanagement.ChatManagement;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Placeholders {

    /**
     * 替换文本中出现的占位符
     * @param rawText 文本
     * @param player 玩家
     * @return 替换过的文本
     */
    public static String replacePlaceHolders(String rawText, Player player){
        if(ChatManagement.getInstance().isUsePapi())
            return PlaceholderAPI.setPlaceholders(player, rawText);
        else
            return rawText;
    }

    /**
     * 替换一个列表文本中出现的占位符
     * @param rawTexts 文本列表
     * @param player 玩家
     * @return 替换过的文本列表
     */
    public static List<String> replaceAll(List<String> rawTexts, Player player){
        List<String> replaced = new ArrayList<>();
        for (String rawText : rawTexts) {
            replaced.add(replacePlaceHolders(rawText, player));
        }
        return replaced;
    }

    @Nullable
    public String getPlaceHolder(OfflinePlayer player, String param){
        switch (param) {
            case "player_prefix":
                return ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId()).getPrefix();
            case "player_suffix":
                return ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId()).getSuffix();
            case "player_prefix_holo":
                return String.join("\n", ChatManagement.getInstance().getUserManager()
                    .getUser(player.getUniqueId()).getPrefixHolo());
            case "player_suffix_holo":
                return String.join("\n", ChatManagement.getInstance().getUserManager()
                    .getUser(player.getUniqueId()).getSuffixHolo());
            case "player_text_holo":
                return String.join("\n", ChatManagement.getInstance().getUserManager()
                    .getUser(player.getUniqueId()).getTextHolo());
            case "player_pm_status":
                return String.valueOf(ChatManagement.getInstance().getUserManager()
                    .getUser(player.getUniqueId()).isPmStatus());
            default:
                return null;
        }
    }
}
