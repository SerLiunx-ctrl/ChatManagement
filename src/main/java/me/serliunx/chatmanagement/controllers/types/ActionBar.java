package me.serliunx.chatmanagement.controllers.types;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.controllers.AbstractController;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.placeholders.Placeholders;
import me.serliunx.chatmanagement.utils.StringUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public final class ActionBar extends AbstractController {

    public ActionBar() {
        super(ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getString("private_message.format_receive", "error"),
                ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getString("private_message.format_send", "error"),
                ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getBoolean("private_message.color_text", true));
    }

    @Override
    public void showMessage(@NotNull String text, Player player, Format format, Set<Player> recipients) {
        String prefix = format.getPrefix();
        String suffix = format.getSuffix();

        prefix = Placeholders.replacePlaceHolders(StringUtils.Color(prefix), player);
        suffix = Placeholders.replacePlaceHolders(StringUtils.Color(suffix), player);
        text = StringUtils.ColorWithPlayer(player, text);

        for (Player p : recipients)
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                    prefix + text + suffix));
    }
}
