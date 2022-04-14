package me.serliunx.chatmanagement.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void asyncPlayerChat(AsyncPlayerChatEvent event){

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

    }
}
