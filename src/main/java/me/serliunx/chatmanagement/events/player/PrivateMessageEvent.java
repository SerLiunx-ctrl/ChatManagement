package me.serliunx.chatmanagement.events.player;

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

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public Player getTargetPlayer(){
        return targetPlayer;
    }

    public void setTargetPlayer(Player targetPlayer){
        this.targetPlayer = targetPlayer;
    }
}
