package me.serliunx.chatmanagement.listener;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entity.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void asyncPlayerChat(AsyncPlayerChatEvent event){
        if(ChatManagement.getInstance().getControllerManager().showMessage(event.getMessage(), event.getPlayer()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(ChatManagement.getInstance().getUserManager().addUser(new User(event.getPlayer().getUniqueId())))
            ChatManagement.getInstance().getLogger().info("数据中不存在该玩家: " + event.getPlayer().getName() + ", " +
                    "已更新该玩家, 并已添加至数据库.");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

    }
}
