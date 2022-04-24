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

    @Override
    public void show(String text, User user, Format format) {
        int maxTicks = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getInt("bossBar.timer",80);
        String barColor = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("bossBar.color", "GREEN");
        String barStyle = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("bossBar.style", "SOLID");
        String prefix = Placeholders.replacePlaceHolders(StringUtils.Color(format.getPrefix()), user.getPlayer());
        String suffix = Placeholders.replacePlaceHolders(StringUtils.Color(format.getSuffix()), user.getPlayer());
        String message = StringUtils.ColorWithPlayer(user.getPlayer(),text);

        for (Player p:Bukkit.getOnlinePlayers()){
            BossBarUtils.showText(p,prefix + message + suffix, BarColor.valueOf(barColor), BarStyle.valueOf(barStyle),
                    1, maxTicks);
        }
    }

    @Override
    public void showPrivateMessage(String text, User user, User target) {

    }
}
