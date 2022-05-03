package me.serliunx.chatmanagement.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class ChatEvent extends CPlayerEvent {
    protected String message;

    public ChatEvent(@NotNull Player who, boolean async, String message) {
        super(who, async);
        this.message = message;
    }

    /**
     * 获取触发该事件的玩家所说的文字. <p>
     * 该方法返回的是已经过滤过的文字.
     * @return 文本
     */
    @NotNull
    public String getMessage(){
        return message;
    }

    /**
     * 重新设置触发该事件的玩家所说的文字
     *
     * @param message 文本
     */
    public void setMessage(@NotNull String message){
        this.message = message;
    }
}
