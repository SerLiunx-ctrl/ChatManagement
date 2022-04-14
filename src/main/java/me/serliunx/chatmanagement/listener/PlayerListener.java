package me.serliunx.chatmanagement.listener;

import me.serliunx.chatmanagement.ChatManagement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void asyncPlayerChat(AsyncPlayerChatEvent event){
        ChatManagement.getInstance().getControllerManager().showMessage(event.getMessage(), event.getPlayer());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

    }
}
