package me.serliunx.chatmanagement.controllers.types;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.controllers.AbstractController;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.placeholders.Placeholders;
import me.serliunx.chatmanagement.utils.StringUtils;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public final class Normal extends AbstractController {

    public Normal() {
        super(ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getString("private_message.format_receive", "error"),
                ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getString("private_message.format_send", "error"),
                ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getBoolean("private_message.color_text", true));
    }

    @Override
    public void showMessage(@NotNull String text, Player player, Format format, Set<Player> recipients) {
        StringBuilder prefix_holo = new StringBuilder();
        StringBuilder suffix_holo = new StringBuilder();
        StringBuilder text_holo = new StringBuilder();

        if(format.getPrefixHolo() != null){
            for(int i = 0;i < format.getPrefixHolo().size(); i++){
                if(i == format.getPrefixHolo().size() - 1)
                    prefix_holo.append(format.getPrefixHolo().get(i));
                else
                    prefix_holo.append(format.getPrefixHolo().get(i)).append("\n");
            }
        }

        if(format.getSuffixHolo() != null){
            for(int i = 0;i < format.getSuffixHolo().size(); i++){
                if(i == format.getSuffixHolo().size() - 1)
                    suffix_holo.append(format.getSuffixHolo().get(i));
                else
                    suffix_holo.append(format.getSuffixHolo().get(i)).append("\n");
            }
        }

        if(format.getTextHolo() != null){
            for(int i = 0;i < format.getTextHolo().size(); i++){
                if(i == format.getTextHolo().size() - 1)
                    text_holo.append(format.getTextHolo().get(i));
                else
                    text_holo.append(format.getTextHolo().get(i)).append("\n");
            }
        }

        TextComponent prefix = new TextComponent(Placeholders.replacePlaceHolders(StringUtils.Color(format.getPrefix()),
                player));
        TextComponent suffix = new TextComponent(Placeholders.replacePlaceHolders(StringUtils.Color(format.getSuffix()),
                player));
        TextComponent tText = new TextComponent(StringUtils.ColorWithPlayer(player, text));

        Text tPrefix_holo = new Text(Placeholders.replacePlaceHolders(StringUtils.Color(prefix_holo.toString()), player));
        Text tSuffix_holo = new Text(Placeholders.replacePlaceHolders(StringUtils.Color(suffix_holo.toString()), player));
        Text tText_holo = new Text(Placeholders.replacePlaceHolders(StringUtils.Color(text_holo.toString()), player));

        prefix.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tPrefix_holo));
        suffix.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tSuffix_holo));
        tText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tText_holo));

        for(Player p: recipients)
            p.spigot().sendMessage(prefix, tText, suffix);
    }
}
