package me.serliunx.chatmanagement.database.entities;

import me.serliunx.chatmanagement.enums.ChatType;

import java.util.List;

/**
 * 聊天格式类, 用于改变聊天格式.
 */
public final class Format implements Cloneable{

    private String name;
    private String permission;
    private ChatType chatType;
    private String prefix;
    private String suffix;
    private int priority;
    private List<String> prefixHolo;
    private List<String> suffixHolo;
    private List<String> textHolo;

    /**
     * 所有属性构造器
     *
     * @param name  聊天格式的名字, 聊天格式的唯一标识. 不能重复
     * @param permission  权限, 在为玩家选择聊天格式时 这是检查项目之一.
     * @param chatType  文字显示的方式: {@link ChatType}
     * @param prefix 前缀, 不是权限组插件中的前缀,指的是玩家的聊天文字前的所有内容
     * @param suffix 后缀, 意义同前缀.
     * @param priority 优先级, 当玩家拥有两个聊天格式的权限时, 会优先选择优先级高的.
     * @param prefixHolo 悬浮文字, 鼠标放在前缀上时显示.
     * @param suffixHolo 悬浮文字, 鼠标放在后缀上时显示.
     * @param textHolo 悬浮文字, 鼠标放在聊天文字上时显示.
     */
    public Format(String name, String permission, ChatType chatType, String prefix, String suffix, int priority, List<String> prefixHolo, List<String> suffixHolo, List<String> textHolo) {
        this.name = name;
        this.permission = permission;
        this.chatType = chatType;
        this.prefix = prefix;
        this.suffix = suffix;
        this.priority = priority;
        this.prefixHolo = prefixHolo;
        this.suffixHolo = suffixHolo;
        this.textHolo = textHolo;
    }

    /**
     * 部分属性构造器, 默认所有悬浮文字为null, 有需要请手动赋值.
     *
     * @param name  聊天格式的名字, 聊天格式的唯一标识. 不能重复
     * @param permission  权限, 在为玩家选择聊天格式时 这是检查项目之一.
     * @param type  文字显示的方式.{@link ChatType}
     * @param prefix 前缀, 不是权限组插件中的前缀,指的是玩家的聊天文字前的所有内容
     * @param suffix 后缀, 意义同前缀.
     * @param priority 优先级, 当玩家拥有两个聊天格式的权限时, 会优先选择优先级高的.
     */
    public Format(String name, String permission, ChatType type, String prefix, String suffix, int priority) {
        this.name = name;
        this.permission = permission;
        this.chatType = type;
        this.prefix = prefix;
        this.suffix = suffix;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    @Override
    public Object clone(){
        try{
            return super.clone();
        }catch (CloneNotSupportedException e){
            return null;
        }
    }
}
