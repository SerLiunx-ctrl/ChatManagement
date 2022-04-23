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

    @NotNull
    public String getMessage(){
        return message;
    }

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

    @NotNull
    public Format getFormat(){
        return format;
    }

    public void setFormat(@NotNull Format format){
        this.format = format;
    }

    @NotNull
    public List<Player> getRecipients(){
        return recipients;
    }
}
