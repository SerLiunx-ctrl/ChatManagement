package me.serliunx.chatmanagement.controllers.types;

import me.serliunx.chatmanagement.controllers.Controller;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.placeholders.Placeholders;
import me.serliunx.chatmanagement.utils.StringUtils;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class Normal implements Controller {

    @Override
    public void show(String text, User user, Format format) {
        StringBuilder prefix_holo = new StringBuilder();
        Player player = user.getPlayer();
        if(format.getPrefixHolo() != null){
            for(int i = 0;i < format.getPrefixHolo().size(); i++){
                if(i == format.getPrefixHolo().size() - 1)
                    prefix_holo.append(format.getPrefixHolo().get(i));
                else
                    prefix_holo.append(format.getPrefixHolo().get(i)).append("\n");
            }
        }

        TextComponent prefix = new TextComponent(Placeholders.replacePlaceHolders(StringUtils.Color(format.getPrefix()),
                player));
        TextComponent suffix = new TextComponent(Placeholders.replacePlaceHolders(StringUtils.Color(format.getSuffix()),
                player));
        Text tPrefix_holo = new Text(Placeholders.replacePlaceHolders(StringUtils.Color(prefix_holo.toString()), player));

        TextComponent tText = new TextComponent(text);

        prefix.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tPrefix_holo));

        for(Player p: Bukkit.getOnlinePlayers())
            p.spigot().sendMessage(prefix, tText, suffix);
    }

    @Override
    public void showPrivateMessage(String text, User user, User target) {

    }
}
