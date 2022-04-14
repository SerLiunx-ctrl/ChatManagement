package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.controllers.Controller;
import me.serliunx.chatmanagement.controllers.types.*;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.enums.ChatType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
            case BOSS_BAR:
                return bossbar;
            case ACTION_BAR:
                return actionbar;
            default:
                return normal;
        }
    }

    /**
     * 以各种方式显示聊天信息, 会匹配玩家格式组{@link Format} 以及聊天方式(控制器){@link Controller}, {@link ChatType}
     * @param text 信息
     * @param player 玩家
     */
    public void showMessage(String text, Player player){
        Format format = ChatManagement.getInstance().getFormatManager().matchPlayerFormat(player);
        User user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());
        if(user != null)
            matchController(format).show(user, format);
    }
}
