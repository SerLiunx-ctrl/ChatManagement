package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.database.entities.User;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {
    private final Map<UUID, User> userMap;

    public UserManager(){
        userMap = new HashMap<>();
    }

    public User getUser(@NotNull UUID uuid){
        return userMap.get(uuid);
    }
}
