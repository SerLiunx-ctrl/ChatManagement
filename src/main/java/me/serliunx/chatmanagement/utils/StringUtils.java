package me.serliunx.chatmanagement.utils;

import me.serliunx.chatmanagement.enums.Permission;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    /**
     * 转换颜色代码: &, 支持转换16进制的颜色代码. 模板: "#[a-fA-F0-9]{6}"
     *
     * @param rawText: 需要翻译颜色代码文字.
     * @return 翻译颜色代码后的文字.
     *
     */
    public static String Color(@NotNull String rawText){
        return ColorNormal(ColorHexCode(rawText));
    }

    /**
     *
     * @param player: 玩家, 用途: 检测是否有权限.
     * @param rawText: 需要翻译颜色代码文字.
     * @return 翻译颜色代码后的文字.
     *
     */
    public static String ColorWithPlayer(@NotNull Player player, @NotNull String rawText){
        if(player.hasPermission(Permission.OTHER_PLAYER_CHATCOLOR.getValue()))
            return ColorNormal(ColorHexCode(rawText));
        return rawText;
    }

    public static String ColorNormal(@NotNull String rawText){
        return ChatColor.translateAlternateColorCodes('&',rawText);
    }

    public static String ColorHexCode(@NotNull String rawText){
        Matcher match = pattern.matcher(rawText);
        while (match.find()){
            String color = rawText.substring(match.start(), match.end());
            rawText = rawText.replace(color,ChatColor.of(color) + "");
            match = pattern.matcher(rawText);
        }
        return rawText;
    }
}
