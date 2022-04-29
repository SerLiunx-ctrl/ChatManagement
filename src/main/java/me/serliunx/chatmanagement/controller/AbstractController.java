package me.serliunx.chatmanagement.controller;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.enums.ChatType;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.util.BossBarUtils;
import me.serliunx.chatmanagement.util.StringUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import java.util.List;

public abstract class AbstractController implements Controller{
    private final String formatPmReceive, formatPmSend;
    private final boolean pmColor;
    private final StringBuilder holoBuilder;

    public AbstractController(String formatPmReceive, String formatPmSend, boolean pmColor) {
        this.formatPmReceive = formatPmReceive;
        this.formatPmSend = formatPmSend;
        this.pmColor = pmColor;
        List<String> formatHolo = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getStringList("private_message.text_holo");
        holoBuilder = new StringBuilder();
        if(!formatHolo.isEmpty()){
            for(int i = 0;i<formatHolo.size();i++){
                holoBuilder.append(i == formatHolo.size() - 1 ? formatHolo.get(i) : formatHolo.get(i) + "\n");
            }
        }
    }

    public void showPrivateMessage(ChatType type, String text, Player player, Player targetPlayer){
        String message = pmColor ? StringUtils.ColorWithPlayer(player,text) : text;
        String formatReceive =StringUtils.Color(formatPmReceive.replace("{sender_name}",player.getName()));
        String formatSend = StringUtils.Color(formatPmSend.replace("{receiver_name}", targetPlayer.getName()));
        switch (type){
            case ACTION_BAR:
                targetPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(formatReceive + message));
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(formatSend + message));
                break;
            case BOSS_BAR:
                BossBarUtils.showText(targetPlayer, formatReceive + message,1,80);
                BossBarUtils.showText(player, formatSend + message, 1, 80);
                break;
            default:{
                TextComponent formatReceiveComponent = new TextComponent(formatReceive);
                TextComponent formatSendComponent = new TextComponent(formatSend);
                TextComponent messageComponent = new TextComponent(message);

                formatReceiveComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(
                        StringUtils.Color(holoBuilder.toString()))));

                String holoCommand = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                        .getConfiguration().getString("private_message.holo_command","error").replace("{sender_name}",
                                player.getName());
                formatReceiveComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, holoCommand));

                targetPlayer.spigot().sendMessage(formatReceiveComponent, messageComponent);
                player.spigot().sendMessage(formatSendComponent, messageComponent);
            }
        }

    }
}
