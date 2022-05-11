package me.serliunx.chatmanagement.listener;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    protected final ChatManagement instance;

    public PlayerListener() {
        this.instance = ChatManagement.getInstance();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(instance.getUserManager().addUser(new User(event.getPlayer().getUniqueId())))
            instance.getLogger().info(instance.getLanguage()
                    .getSingleLine("player_data_created").replace("{0}", event.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        User mainUser = instance.getUserManager().getUser(event.getPlayer().getUniqueId());
        if(!mainUser.isPmStatus())
            return;

        for(Player p:Bukkit.getOnlinePlayers()){
            //排除该用户.
            if(p.getUniqueId() == event.getPlayer().getUniqueId()) continue;

            User anotherUser = instance.getUserManager().getUser(p.getUniqueId());
            if(anotherUser == null) continue;
            if(!anotherUser.isPmStatus()) continue;
            if(anotherUser.getAnotherUUID() == mainUser.getUuid()){
                anotherUser.setAnotherUUID(null);
                p.sendMessage(instance.getLanguage().getSingleLine("privatemessage_targetOffline")
                        .replace("{0}",event.getPlayer().getName()));

                return;
            }
        }
    }
}
