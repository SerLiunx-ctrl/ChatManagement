package me.serliunx.chatmanagement.util;

import me.serliunx.chatmanagement.enums.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
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

    /**
     * 返回一个文本组件
     *
     * @param text 文本
     * @param holo 悬浮文字
     * @return 文本组件
     */
    public static TextComponent newTextComponent(@NotNull String text, @Nullable List<String> holo){
        TextComponent textComponent = new TextComponent(Color(text));
        StringBuilder stringBuilder = new StringBuilder();
        if(holo != null)
            for(int i = 0; i < holo.size(); i++){
                stringBuilder.append(i == holo.size() - 1 ? holo.get(i) : holo.get(i) + "\n");
            }

        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(Color(stringBuilder.toString()))));
        return textComponent;
    }
}
