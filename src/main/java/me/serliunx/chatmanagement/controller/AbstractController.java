package me.serliunx.chatmanagement.controller;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.enums.ChatType;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.util.BossBarUtils;
import me.serliunx.chatmanagement.util.StringUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import java.util.List;

public abstract class AbstractController implements Controller{
    private final String formatPmReceive, formatPmSend, holoCommand;
    private final boolean pmColor;
    private final List<String> formatHolo;

    public AbstractController() {
        this.formatPmReceive = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("private_message.format_receive", "error");
        this.formatPmSend = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("private_message.format_send", "error");
        this.pmColor = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getBoolean("private_message.color_text", true);
        formatHolo = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getStringList("private_message.text_holo");
        holoCommand = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("private_message.holo_command","error");

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
                TextComponent formatSendComponent = new TextComponent(formatSend);
                TextComponent messageComponent = new TextComponent(message);
                TextComponent formatReceiveComponent = StringUtils.newTextComponent(formatReceive, formatHolo,
                        holoCommand.replace("{sender_name}",player.getName()));

                targetPlayer.spigot().sendMessage(formatReceiveComponent, messageComponent);
                player.spigot().sendMessage(formatSendComponent, messageComponent);
            }
        }

    }
}
