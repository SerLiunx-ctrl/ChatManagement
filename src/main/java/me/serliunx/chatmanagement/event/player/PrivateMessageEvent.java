package me.serliunx.chatmanagement.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PrivateMessageEvent extends CPlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private String message;
    private Player targetPlayer;

    public PrivateMessageEvent(final boolean async, @NotNull Player player, @NotNull Player targetPlayer, @NotNull String message){
        super(player, async);
        this.targetPlayer = targetPlayer;
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * 设置触发该事件的私聊中的文本消息
     * @param message 文本消息
     */
    public void setMessage(@NotNull String message){
        this.message = message;
    }

    /**
     * 获取触发该事件的私聊中的文本消息
     * @return 文本消息
     */
    @NotNull
    public String getMessage(){
        return message;
    }

    /**
     * 获取触发该事件的私聊中的接收对象
     * @return 接收对象
     */
    @NotNull
    public Player getTargetPlayer(){
        return targetPlayer;
    }

    /**
     * 修改触发该事件的私聊中的接受对象
     * @param targetPlayer 接受对象
     */
    public void setTargetPlayer(@NotNull Player targetPlayer){
        this.targetPlayer = targetPlayer;
    }
}
