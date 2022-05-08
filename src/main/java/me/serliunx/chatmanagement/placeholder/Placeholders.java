package me.serliunx.chatmanagement.placeholder;

import me.serliunx.chatmanagement.ChatManagement;
import me.clip.placeholderapi.PlaceholderAPI;
import me.serliunx.chatmanagement.database.entity.User;
import me.serliunx.chatmanagement.enums.DefaultValue;
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
        return ChatManagement.getInstance().isUsePapi() ? PlaceholderAPI.setPlaceholders(player, rawText):
                rawText;
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
        User user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());
        if(user == null) return null;

        switch (param) {
            case "player_prefix":
                return user.getPrefix().equals(DefaultValue.PREFIX.getValue()) ?
                        ChatManagement.getInstance().getLanguage().getSingleLine("placeholder_default") :
                        user.getPrefix();
            case "player_suffix":
                return user.getSuffix().equals(DefaultValue.SUFFIX.getValue()) ?
                        ChatManagement.getInstance().getLanguage().getSingleLine("placeholder_default") :
                        user.getSuffix();
            case "player_prefix_holo":
                return user.getPrefixHolo() == null ? null : String.join("\n", user.getPrefixHolo());
            case "player_suffix_holo":
                return user.getSuffixHolo() == null ? null : String.join("\n", user.getSuffixHolo());
            case "player_text_holo":
                return user.getTextHolo() == null ? null : String.join("\n", user.getTextHolo());
            case "player_pmstatus":
                return user.isPmStatus() ? ChatManagement.getInstance().getLanguage().getSingleLine("placeholder_enable"):
                        ChatManagement.getInstance().getLanguage().getSingleLine("placeholder_disable");
            case "player_chatstatus":
                return user.getChatStatus() ? ChatManagement.getInstance().getLanguage().getSingleLine("placeholder_enable"):
                        ChatManagement.getInstance().getLanguage().getSingleLine("placeholder_disable");
            default:
                return ChatManagement.getInstance().getLanguage().getSingleLine("placeholder_unknown");
        }
    }
}
