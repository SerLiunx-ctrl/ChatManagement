package me.serliunx.chatmanagement.controllers.types;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.controllers.Controller;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.placeholders.Placeholders;
import me.serliunx.chatmanagement.utils.BossBarUtils;
import me.serliunx.chatmanagement.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

public final class BossBar implements Controller {

    private final int maxTicks;
    private final String barColor, barStyle, formatPmReceive, formatPmSend;
    private final boolean color_pm;

    public BossBar(){
        formatPmReceive = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getString("private_message.format_receive", "error");
        formatPmSend = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getString("private_message.format_send", "error");
        color_pm = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getBoolean("private_message.color_text", true);
        maxTicks = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getInt("bossBar.timer",80);
        barColor = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("bossBar.color", "GREEN");
        barStyle = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("bossBar.style", "SOLID");
    }

    @Override
    public void show(String text, User user, Format format) {
        String prefix = Placeholders.replacePlaceHolders(StringUtils.Color(format.getPrefix()), user.getPlayer());
        String suffix = Placeholders.replacePlaceHolders(StringUtils.Color(format.getSuffix()), user.getPlayer());
        String message = StringUtils.ColorWithPlayer(user.getPlayer(),text);

        Bukkit.getOnlinePlayers().forEach( p -> BossBarUtils.showText(p,prefix + message + suffix, BarColor.valueOf(barColor),
                BarStyle.valueOf(barStyle), 1, maxTicks));
    }

    @Override
    public void showPrivateMessage(String text, User user, User target) {
        Player player = user.getPlayer();
        Player targetPlayer = target.getPlayer();
        String receive = StringUtils.Color(formatPmReceive.replace("{sender_name}", player.getName()));
        String send = StringUtils.Color(formatPmSend.replace("{receiver_name}", targetPlayer.getName()));
        String message = color_pm ? StringUtils.ColorWithPlayer(player, text) : text;
        BossBarUtils.showText(player, send+message,BarColor.valueOf(barColor),BarStyle.valueOf(barStyle),1,maxTicks);
        BossBarUtils.showText(targetPlayer, receive+message,BarColor.valueOf(barColor),BarStyle.valueOf(barStyle),1,maxTicks);
    }
}
