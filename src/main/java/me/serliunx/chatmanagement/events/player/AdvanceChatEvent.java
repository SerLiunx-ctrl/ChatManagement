package me.serliunx.chatmanagement.events.player;

import me.serliunx.chatmanagement.database.entities.Format;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class AdvanceChatEvent extends CPlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private Format format;
    private String message;
    private final List<Player> recipients;

    public AdvanceChatEvent(final boolean async, @NotNull Player player, @NotNull Format format, @NotNull String message,
                            List<Player> recipients){
        super(player, async);
        this.format = format;
        this.message = message;
        this.recipients = recipients;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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
     * 获取触发该事件的玩家的聊天格式
     *
     * @return 聊天格式
     */
    @NotNull
    public Format getFormat(){
        return format;
    }

    /**
     * 设置触发该事件的玩家的聊天格式 <p>
     * 通过该方法设置的聊天格式只会修改本次事件的聊天格式<p>
     * 并不会对玩家本身的聊天格式产生影响
     * @param format 聊天格式
     */
    public void setFormat(@NotNull Format format){
        this.format = format;
    }

    @NotNull
    public List<Player> getRecipients(){
        return recipients;
    }
}
