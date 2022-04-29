package me.serliunx.chatmanagement.controllers.types;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.controllers.AbstractController;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.placeholders.Placeholders;
import me.serliunx.chatmanagement.utils.BossBarUtils;
import me.serliunx.chatmanagement.utils.StringUtils;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public final class BossBar extends AbstractController {

    private final int maxTicks;
    private final String barColor, barStyle;

    public BossBar(){
        super(ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getString("private_message.format_receive", "error"),
                ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getString("private_message.format_send", "error"),
                ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getBoolean("private_message.color_text", true));
        maxTicks = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getInt("bossBar.timer",80);
        barColor = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("bossBar.color", "GREEN");
        barStyle = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("bossBar.style", "SOLID");
    }

    @Override
    public void showMessage(@NotNull String text, Player player, Format format, Set<Player> recipients) {
        String prefix = Placeholders.replacePlaceHolders(StringUtils.Color(format.getPrefix()), player);
        String suffix = Placeholders.replacePlaceHolders(StringUtils.Color(format.getSuffix()), player);
        String message = StringUtils.ColorWithPlayer(player,text);

        recipients.forEach( p -> BossBarUtils.showText(p,prefix + message + suffix, BarColor.valueOf(barColor),
                BarStyle.valueOf(barStyle), 1, maxTicks));
    }
}
