package me.serliunx.chatmanagement.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public abstract class CPlayerEvent extends Event {
    protected Player player;

    public CPlayerEvent(@NotNull final Player who) {
        player = who;
    }

    CPlayerEvent(@NotNull final Player who, boolean async) {
        super(async);
        player = who;
    }

    /**
     * 获取该事件的触发玩家.
     *
     * @return 玩家: 触发该事件的玩家
     */
    @NotNull
    public final Player getPlayer() {
        return player;
    }
}
