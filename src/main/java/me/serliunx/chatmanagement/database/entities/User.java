package me.serliunx.chatmanagement.database.entities;

import me.serliunx.chatmanagement.enums.ChatType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * 本插件中的用户类.
 */
public final class User {

    private String prefix;
    private String suffix;
    private List<String> prefixHolo;
    private List<String> suffixHolo;
    private List<String> textHolo;
    private Map<String, Group> groups;
    private ChatType chatType;
    private UUID uuid;
    private UUID anotherUUID;
    private boolean pmStatus;

    /**
     * 默认构造器
     *
     * @param prefix 前缀, 不是权限组插件中的前缀,指的是玩家的聊天文字前的所有内容
     * @param suffix 后缀, 意义同前缀.
     * @param prefixHolo 悬浮文字, 鼠标放在前缀上时显示.
     * @param suffixHolo 悬浮文字, 鼠标放在后缀上时显示.
     * @param textHolo 悬浮文字, 鼠标放在聊天文字上时显示.
     * @param chatType  文字显示的方式: {@link ChatType}
     * @param uuid 玩家自己的UUID, 该实体唯一标识符
     * @param pmStatus 私聊的状态.
     */
    public User(String prefix, String suffix, List<String> prefixHolo, List<String> suffixHolo, List<String> textHolo, ChatType chatType, UUID uuid, boolean pmStatus) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.prefixHolo = prefixHolo;
        this.suffixHolo = suffixHolo;
        this.textHolo = textHolo;
        this.chatType = chatType;
        this.uuid = uuid;
        this.pmStatus = pmStatus;
        groups = new HashMap<>();
    }

    /**
     * 简易构造器, 前缀、后缀、聊天文字的悬浮内容均为null, 有需要请手动赋值.
     *
     * @param prefix 前缀, 不是权限组插件中的前缀,指的是玩家的聊天文字前的所有内容
     * @param suffix 后缀, 意义同前缀.
     * @param chatType  文字显示的方式: {@link ChatType}
     * @param uuid 玩家自己的UUID, 该实体唯一标识符
     * @param pmStatus 私聊的状态.
     */
    public User(String prefix, String suffix, ChatType chatType, UUID uuid, boolean pmStatus) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.chatType = chatType;
        this.uuid = uuid;
        this.pmStatus = pmStatus;
        groups = new HashMap<>();
    }

    /**
     * 保留空参数构造器.
     */
    public User(){}

    /**
     * 获取用户的前缀
     * @return 前缀
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * 设置用户的前缀
     * @param prefix 前缀
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 获取玩家的后缀
     * @return 后缀
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 设置玩家的后缀
     * @param suffix 后缀
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * 获取玩家前缀的悬浮文字
     * @return 前缀悬浮文字
     */
    public List<String> getPrefixHolo() {
        return prefixHolo;
    }

    /**
     * 设置前缀的悬浮文字
     * @param prefixHolo 前缀悬浮文字
     */
    public void setPrefixHolo(List<String> prefixHolo) {
        this.prefixHolo = prefixHolo;
    }

    /**
     * 获取玩家的后缀悬浮文字
     * @return 后缀悬浮文字
     */
    public List<String> getSuffixHolo() {
        return suffixHolo;
    }

    /**
     * 设置玩家的后缀悬浮文字
     * @param suffixHolo 后缀悬浮文字
     */
    public void setSuffixHolo(List<String> suffixHolo) {
        this.suffixHolo = suffixHolo;
    }

    /**
     * 获取玩家文字内容的悬浮文字
     * @return 悬浮文字
     */
    public List<String> getTextHolo() {
        return textHolo;
    }

    /**
     * 设置玩家文字内容的悬浮文字
     * @param textHolo 悬浮文字
     */
    public void setTextHolo(List<String> textHolo) {
        this.textHolo = textHolo;
    }

    /**
     * 获取玩家的聊天文字显示方式
     * @return  文字显示方式 {@link ChatType}
     */
    public ChatType getChatType() {
        return chatType;
    }

    /**
     * 设置玩家的聊天文字显示方式
     * @param chatType 文字显示方式 {@link ChatType}
     */
    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    /**
     * 获取玩家的 UUID
     * @return UUID {@link UUID}
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     *
     * 获取玩家当前私聊对象的UUID
     *
     * @return 返回私聊对象的 {@link UUID}
     */
    public UUID getAnotherUUID() {
        return anotherUUID;
    }

    /**
     * 设置玩家的私聊对象
     *
     * @param anotherUUID UUID
     */
    public void setAnotherUUID(UUID anotherUUID) {
        this.anotherUUID = anotherUUID;
    }

    /**
     * 获取玩家的私聊状态
     * @return 是否在私聊, 如果玩家的在私聊中返回真, 否则返回假
     */
    public boolean isPmStatus() {
        return pmStatus;
    }

    /**
     * 设置玩家的私聊状态
     * @param pmStatus 私聊状态
     */
    public void setPmStatus(boolean pmStatus) {
        this.pmStatus = pmStatus;
    }

    /**
     * 获取玩家加入的所有群组
     * @return 群组
     */
    public Map<String, Group> getGroups() {
        return groups;
    }

    /**
     * 设置玩家的群组
     * @param groups 群组
     */
    public void setGroups(Map<String, Group> groups) {
        this.groups = groups;
    }

    /**
     * 将玩家添加到一个群组中, 无法重复.
     * @param group 群组
     * @return 添加成功返回真, 否则返回假
     */
    public boolean addGroup(@NotNull Group group){
        if(groups.containsKey(group.getName()))
            return false;
        groups.put(group.getName(), group);
        return true;
    }

    /**
     *  玩家离开指定群组
     *
     * @param group 群组
     * @return 离开成功返回真, 否则返回假
     */
    public boolean leaveGroup(@NotNull Group group){
        return false;
    }
}
