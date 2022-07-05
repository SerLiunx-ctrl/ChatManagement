package me.serliunx.chatmanagement.controller.type;

import me.serliunx.chatmanagement.controller.AbstractController;
import me.serliunx.chatmanagement.database.entity.Format;
import me.serliunx.chatmanagement.placeholder.Placeholders;
import me.serliunx.chatmanagement.util.StringUtils;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public final class Normal extends AbstractController {

    public Normal() {
        super();
    }

    @Override
    public void showMessage(@NotNull String text, Player player, Format format, Set<Player> recipients) {
        TextComponent prefix = StringUtils.newTextComponent(Placeholders.replacePlaceHolders(format.getPrefix(), player),
                Placeholders.replaceAll(format.getPrefixHolo(), player));

        TextComponent suffix = StringUtils.newTextComponent(Placeholders.replacePlaceHolders(format.getSuffix(), player),
                Placeholders.replaceAll(format.getSuffixHolo(), player));

        TextComponent message = (TextComponent) StringUtils.translateColorCodesToTextComponent(text, player);
        message.setHoverEvent(StringUtils.newHoverEvent(Placeholders.replaceAll(format.getTextHolo(), player),
                HoverEvent.Action.SHOW_TEXT));

        for(Player p: recipients)
            p.spigot().sendMessage(prefix, message, suffix);
    }

}
