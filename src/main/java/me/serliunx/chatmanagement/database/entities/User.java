package me.serliunx.chatmanagement.database.entities;

import me.serliunx.chatmanagement.enums.ChatType;

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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public List<String> getPrefixHolo() {
        return prefixHolo;
    }

    public void setPrefixHolo(List<String> prefixHolo) {
        this.prefixHolo = prefixHolo;
    }

    public List<String> getSuffixHolo() {
        return suffixHolo;
    }

    public void setSuffixHolo(List<String> suffixHolo) {
        this.suffixHolo = suffixHolo;
    }

    public List<String> getTextHolo() {
        return textHolo;
    }

    public void setTextHolo(List<String> textHolo) {
        this.textHolo = textHolo;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public boolean isPmStatus() {
        return pmStatus;
    }

    public void setPmStatus(boolean pmStatus) {
        this.pmStatus = pmStatus;
    }

    public Map<String, Group> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, Group> groups) {
        this.groups = groups;
    }

    public boolean addGroup(Group group){
        return false;
    }
}
