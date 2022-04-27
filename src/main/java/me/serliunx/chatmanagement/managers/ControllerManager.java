package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.controllers.Controller;
import me.serliunx.chatmanagement.controllers.types.*;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.enums.ChatType;
import me.serliunx.chatmanagement.events.player.AdvanceChatEvent;
import me.serliunx.chatmanagement.events.player.PrivateMessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ControllerManager {

    private final Controller normal,actionbar,bossbar;

    public ControllerManager(){
        normal = new Normal();
        actionbar = new ActionBar();
        bossbar = new BossBar();
    }

    /**
     * 匹配格式组的聊天控制器,
     * @param format 格式组
     * @return 返回一个控制器, Normal, ActionBar, BossBar {@link ChatType}
     */
    public @NotNull Controller matchController(@NotNull Format format){
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
    public boolean showMessage(String text, Player player){
        Format format = ChatManagement.getInstance().getFormatManager().matchPlayerFormat(player);
        User user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());
        if(user != null){
            if(isInPm(user)){
                //如果玩家在私聊状态中.
                Player targetPlayer = Bukkit.getPlayer(user.getAnotherUUID());
                if(targetPlayer == null)
                    return false;

                PrivateMessageEvent privateMessageEvent = new PrivateMessageEvent(true, player, targetPlayer, text);
                Bukkit.getPluginManager().callEvent(privateMessageEvent);
                if(privateMessageEvent.isCancelled()) return false;

                matchController(format).showPrivateMessage(privateMessageEvent.getMessage(), user,
                        ChatManagement.getInstance().getUserManager().getUser(privateMessageEvent.getTargetPlayer().getUniqueId()));
                return true;
            }

            List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            AdvanceChatEvent advanceChatEvent = new AdvanceChatEvent(true, player, format, text, players);
            Bukkit.getPluginManager().callEvent(advanceChatEvent);
            if(advanceChatEvent.isCancelled()) return false;
            matchController(format).show(advanceChatEvent.getMessage(), user, advanceChatEvent.getFormat());
        }
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
