package me.serliunx.chatmanagement.manager;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entity.User;
import me.serliunx.chatmanagement.enums.DriverType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.sql.SQLException;
import java.util.*;

public class UserManager {
    private Map<UUID, User> userMap = new HashMap<>();

    public UserManager(){
        loadUsers();
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
    private void loadUsers(){
        userMap.clear();
        userMap = ChatManagement.getInstance().getSqlManager().loadPlayers();
        ChatManagement.getInstance().getLogger().info(ChatManagement.getInstance().getLanguage()
                .getSingleLine("user_loaded").replace("{0}", String.valueOf(userMap.size())));

    }

    /**
     * 快速获取一个用户, 用于快速添加用户
     * <p>
     * 返回的用户没有设置任何属性.
     * @param player 玩家
     * @return 一个新用户
     */
    @Deprecated
    public User fromPlayer(Player player){
        return new User(player.getUniqueId());
    }

    /**
     * 获取所有从数据库中载入的用户
     * @return 用户
     */
    public List<User> getUsers(){
        return new ArrayList<>(userMap.values());
    }
}
