package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.enums.DriverType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {
    private Map<UUID, User> userMap = new HashMap<>();;

    public UserManager(){
        loadPlayers();
    }

    /**
     * 获取一个用户
     * @param uuid  用户的UUID
     * @return 用户
     */
    public User getUser(@NotNull UUID uuid){
        return userMap.get(uuid);
    }

    /**
     * 向用户列表中添加一个用户, 如果数据库不存在该玩家将会添加至数据库中.
     * @param user 用户
     * @return 添加成功返回真, 否则返回假
     */
    public boolean addUser(@NotNull User user){
        if(userMap.containsKey(user.getUuid()))
            return false;
        userMap.put(user.getUuid(), user);

        try {
            ChatManagement.getInstance().getSqlManager().createPlayer(user);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 从数据库中 {@link DriverType} 重载所有用户, 首先会清空 userMap 中的.
     */
    private void loadPlayers(){
        userMap.clear();
        userMap = ChatManagement.getInstance().getSqlManager().loadPlayers();
        ChatManagement.getInstance().getLogger().info("loaded " + userMap.size() + " players");
    }

    public User fromPlayer(Player player){
        return new User(player.getUniqueId());
    }

}
