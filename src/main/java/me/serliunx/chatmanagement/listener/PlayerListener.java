package me.serliunx.chatmanagement.listener;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
            ChatManagement.getInstance().getLogger().info(ChatManagement.getInstance().getLanguage()
                    .getSingleLine("player_data_created").replace("{0}", event.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        User mainUser = ChatManagement.getInstance().getUserManager().getUser(event.getPlayer().getUniqueId());
        if(!mainUser.isPmStatus())
            return;

        for(Player p:Bukkit.getOnlinePlayers()){
            //排除该用户.
            if(p.getUniqueId() == event.getPlayer().getUniqueId()) continue;

            User anotherUser = ChatManagement.getInstance().getUserManager().getUser(p.getUniqueId());
            if(anotherUser == null) continue;
            if(!anotherUser.isPmStatus()) continue;
            if(anotherUser.getAnotherUUID() == mainUser.getUuid()){
                anotherUser.setAnotherUUID(null);
                p.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("privatemessage_targetOffline")
                        .replace("{0}",event.getPlayer().getName()));

                return;
            }
        }
    }
}
