package me.serliunx.chatmanagement.events.player;

import me.serliunx.chatmanagement.database.entities.Filter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Map;

public class FiltrationEvent extends CPlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private final Map<Filter,List<String>> filterListMap;
    private final String message;

    public FiltrationEvent(final boolean async, @NotNull Player player, @NotNull String message,
                           @NotNull Map<Filter,List<String>> filterListMap){
        super(player, async);
        this.message = message;
        this.filterListMap = filterListMap;
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
    public Map<Filter, List<String>> getFilterListMap() {
        return filterListMap;
    }
}
