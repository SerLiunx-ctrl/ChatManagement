package me.serliunx.chatmanagement.controllers.types;

import me.serliunx.chatmanagement.controllers.Controller;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.placeholders.Placeholders;
import me.serliunx.chatmanagement.utils.StringUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class ActionBar implements Controller {

    @Override
    public void show(String text, User user, Format format) {
        Player player = user.getPlayer();
        String prefix = format.getPrefix();
        String suffix = format.getSuffix();

        prefix = Placeholders.replacePlaceHolders(StringUtils.Color(prefix), player);
        suffix = Placeholders.replacePlaceHolders(StringUtils.Color(suffix), player);
        text = StringUtils.ColorWithPlayer(player,text);

        for(Player p : Bukkit.getOnlinePlayers())
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                    prefix + text + suffix));
    }

    @Override
    public void showPrivateMessage(String text, User user, User target) {

    }
}
