package me.serliunx.chatmanagement.api;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * ChatManagement API
 * 请直接使用ChatmanagementAPI.XXX
 * 需要添加你自己的过滤器请继承{@link FilterExpansion}
 */
public class ChatManagementAPI {
    private static ChatManagementAPI instance;
    private ChatManagementAPI(){}

    /**
     * 获取指定用户 {@link UUID}
     * @param uuid 用户UUID
     * @return 所匹配到的用户
     */
    @Nullable
    public static User getUser(@NotNull UUID uuid){
        return ChatManagement.getInstance().getUserManager().getUser(uuid);
    }

    /**
     * 使用本插件中的过滤功能来过滤文字<p>
     * "会触发过滤器事件: {@link me.serliunx.chatmanagement.event.player.FiltrationEvent}"
     * @param uuid 用户的UUID
     * @param text 需要过滤的文本
     * @return 过滤后的文字
     */
    @NotNull
    public static String filter(@NotNull UUID uuid, @NotNull String text){
        User user  = getUser(uuid);
        return ChatManagement.getInstance().getFilterManager().filter(user, text);
    }

    @NotNull
    public static  ChatManagementAPI getInstance() {
        return instance;
    }
}
