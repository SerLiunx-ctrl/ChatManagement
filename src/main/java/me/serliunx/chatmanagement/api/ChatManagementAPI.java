package me.serliunx.chatmanagement.api;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * ChatManagement API
 */
public class ChatManagementAPI {
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
     * <li> "会触发过滤器事件: {@link me.serliunx.chatmanagement.api.event.player.FiltrationEvent}"
     * @param uuid 用户的UUID
     * @param text 需要过滤的文本
     * @return 过滤后的文字
     */
    @NotNull
    public static String filter(@NotNull UUID uuid, @NotNull String text){
        User user  = getUser(uuid);
        if(user == null) return text;
        return ChatManagement.getInstance().getFilterManager().filter(user, text);
    }

    /**
     * 使用本插件中的过滤功能来过滤文字<p>
     *<li>该函数属于通用方法, 无需检查对应的玩家权限.
     *<li>将过滤文字中所有符合已启用的过滤器所包含的值
     *<li>不会触发 {@link me.serliunx.chatmanagement.api.event.player.FiltrationEvent}
     * @param rawText 需要过滤的文本
     * @return 过滤后的文字
     */
    @NotNull
    public static String filter(@NotNull String rawText){
        return  ChatManagement.getInstance().getFilterManager().filter(rawText);
    }

}
