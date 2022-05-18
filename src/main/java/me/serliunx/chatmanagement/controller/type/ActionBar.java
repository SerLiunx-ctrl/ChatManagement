package me.serliunx.chatmanagement.controller.type;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.controller.AbstractController;
import me.serliunx.chatmanagement.database.entity.Format;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.placeholder.Placeholders;
import me.serliunx.chatmanagement.util.StringUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public final class ActionBar extends AbstractController {

    public ActionBar() {
        super();
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
