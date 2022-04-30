package me.serliunx.chatmanagement.manager;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.controller.AbstractController;
import me.serliunx.chatmanagement.controller.Controller;
import me.serliunx.chatmanagement.controller.type.*;
import me.serliunx.chatmanagement.database.entity.Format;
import me.serliunx.chatmanagement.database.entity.User;
import me.serliunx.chatmanagement.enums.ChatType;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.event.player.AdvanceChatEvent;
import me.serliunx.chatmanagement.event.player.PrivateMessageEvent;
import me.serliunx.chatmanagement.util.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.time.Duration;
import java.util.*;

public class ControllerManager {

    private final AbstractController normal,actionbar,bossbar;
    private final FileConfiguration config;
    private final boolean publicFilter,pmFilter;
    private final double cooldownInSeconds;
    private CooldownProvider<Player> cooldownProvider;

    public ControllerManager(){
        normal = new Normal();
        actionbar = new ActionBar();
        bossbar = new BossBar();
        config = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue()).getConfiguration();
        publicFilter = config.getBoolean("filter.enable",true);
        pmFilter = config.getBoolean("filter.enable_pm",true);
        cooldownInSeconds = config.getDouble("general_message.cooldown",2.0);
    }

    public CooldownProvider<Player> getCooldownProvider() {
        if (cooldownProvider == null) {
            this.cooldownProvider = CooldownProvider.newInstance(Duration.ofSeconds(new Double(cooldownInSeconds).longValue()));
        }
        return cooldownProvider;
    }

    /**
     * 匹配格式组的聊天控制器,
     * @param format 格式组
     * @return 返回一个控制器, Normal, ActionBar, BossBar {@link ChatType}
     */
    public @NotNull AbstractController matchController(@NotNull Format format){
        switch (format.getChatType()){
            case ACTION_BAR:
                return actionbar;
            case BOSS_BAR:
                return bossbar;
            default:
                return normal;
        }
    }

    /**
     * 以各种方式显示聊天信息, 会匹配玩家格式组{@link Format} 以及聊天方式(控制器){@link Controller}, {@link ChatType}
     * @param text 信息
     * @param player 玩家
     */
    public boolean showMessage(@NotNull String text, @NotNull Player player){
        CooldownProvider<Player> cooldownProvider = getCooldownProvider();
        if(cooldownProvider.isOnCooldown(player)){
            Duration remainingTime = cooldownProvider.getRemainingTime(player);
            String formattedTime = TimeUtils.formatDuration("{seconds}", remainingTime);
            player.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("in_cooldown")
                    .replace("{seconds}", formattedTime));
            return true;
        }

        Format format = ChatManagement.getInstance().getFormatManager().matchPlayerFormat(player);
        User user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());

        if(!user.getChatStatus()){
            player.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("chat_off"));
            return true;
        }

        getCooldownProvider().applyCooldown(player);
        return isInPm(user) ? showPm(format, text, player) : showPublic(format, text, player);
    }

    private boolean showPm(Format format, String text, Player player){
        User user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());
        Player targetPlayer = Bukkit.getPlayer(user.getAnotherUUID());
        if(targetPlayer == null)
            return true;
        User targetUser = ChatManagement.getInstance().getUserManager().getUser(targetPlayer.getUniqueId());
        //检测是否主动退出私聊
        if(text.equals(config.getString("private_message.exit_text","exit"))){
            player.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("privatemessage_exit"));
            user.setAnotherUUID(null);
            return true;
        }

        //检测对方是否中途关闭了私聊.
        if(!targetUser.isPmStatus()){
            user.setAnotherUUID(null);
            player.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("privatemessage_off"));
            return true;
        }

        PrivateMessageEvent privateMessageEvent = new PrivateMessageEvent(true, player, targetPlayer, text);
        Bukkit.getPluginManager().callEvent(privateMessageEvent);
        if(privateMessageEvent.isCancelled()) return showPublic(format, text, player);

        text = pmFilter ? ChatManagement.getInstance().getFilterManager().filter(user,text) : text;
        matchController(format).showPrivateMessage(format.getChatType(),text,player,targetPlayer);
        return true;
    }

    private boolean showPublic(Format format, String text, Player player){
        Set<Player> usersChatOn = new HashSet<>();
        for(Player p:Bukkit.getOnlinePlayers()){
            User u = ChatManagement.getInstance().getUserManager().getUser(p.getUniqueId());
            if(u != null && u.getChatStatus())
                usersChatOn.add(p);
        }

        AdvanceChatEvent advanceChatEvent = new AdvanceChatEvent(true, player, format, text, usersChatOn);
        Bukkit.getPluginManager().callEvent(advanceChatEvent);

        if(advanceChatEvent.isCancelled()) return false;
        String eventString = publicFilter ? ChatManagement.getInstance().getFilterManager()
                .filter(player,advanceChatEvent.getMessage()) : advanceChatEvent.getMessage();
        matchController(format).showMessage(eventString, player, advanceChatEvent.getFormat(), advanceChatEvent.getRecipients());
        return true;
    }

    /**
     * 检测一个玩家是不是在私聊模式中.
     *
     * @param user 玩家.
     * @return 如果在私聊中返回真, 否则返回假.
     */
    public boolean isInPm(User user){
        return user.isPmStatus() && user.getAnotherUUID() != null;
    }
}
