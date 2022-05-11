package me.serliunx.chatmanagement.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener extends PlayerListener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void asyncPlayerChat(AsyncPlayerChatEvent event){
        if(instance.getControllerManager().showMessage(event.getMessage(), event.getPlayer()))
            event.setCancelled(true);
    }
}
