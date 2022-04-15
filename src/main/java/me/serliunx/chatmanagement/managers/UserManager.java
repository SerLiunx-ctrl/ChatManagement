package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.enums.DriverType;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {
    private final Map<UUID, User> userMap;

    public UserManager(){
        userMap = new HashMap<>();
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
     * 向用户列表中添加一个用户
     * @param user 用户
     * @return 添加成功返回真, 否则返回假
     */
    public boolean addUser(@NotNull User user){
        if(userMap.containsKey(user.getUuid()))
            return false;
        userMap.put(user.getUuid(), user);
        return true;
    }

    /**
     * 从数据库中 {@link DriverType} 重载所有用户, 首先会清空 userMap 中的.
     */
    public void loadPlayers(){

    }
}
