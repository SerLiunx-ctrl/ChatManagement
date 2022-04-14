package me.serliunx.chatmanagement.database.entities;

import me.serliunx.chatmanagement.enums.ChatType;

import java.util.List;

/**
 * 聊天格式类, 用于改变聊天格式.
 */
public final class Format implements Cloneable{

    private String name;
    private String permission;
    private ChatType type;
    private String prefix;
    private String suffix;
    private int priority;
    private List<String> holo_prefix;
    private List<String> hole_suffix;
    private List<String> hole_text;

    /**
     * 所有属性构造器
     *
     * @param name  聊天格式的名字, 聊天格式的唯一标识. 不能重复
     * @param permission  权限, 在为玩家选择聊天格式时 这是检查项目之一.
     * @param type  文字显示的方式: {@link ChatType}
     * @param prefix 前缀, 不是权限组插件中的前缀,指的是玩家的聊天文字前的所有内容
     * @param suffix 后缀, 意义同前缀.
     * @param priority 优先级, 当玩家拥有两个聊天格式的权限时, 会优先选择优先级高的.
     * @param holo_prefix 悬浮文字, 鼠标放在前缀上时显示.
     * @param hole_suffix 悬浮文字, 鼠标放在后缀上时显示.
     * @param hole_text 悬浮文字, 鼠标放在聊天文字上时显示.
     */
    public Format(String name, String permission, ChatType type, String prefix, String suffix, int priority, List<String> holo_prefix, List<String> hole_suffix, List<String> hole_text) {
        this.name = name;
        this.permission = permission;
        this.type = type;
        this.prefix = prefix;
        this.suffix = suffix;
        this.priority = priority;
        this.holo_prefix = holo_prefix;
        this.hole_suffix = hole_suffix;
        this.hole_text = hole_text;
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
        this.type = type;
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

    public ChatType getType() {
        return type;
    }

    public void setType(ChatType type) {
        this.type = type;
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

    public List<String> getHolo_prefix() {
        return holo_prefix;
    }

    public void setHolo_prefix(List<String> holo_prefix) {
        this.holo_prefix = holo_prefix;
    }

    public List<String> getHole_suffix() {
        return hole_suffix;
    }

    public void setHole_suffix(List<String> hole_suffix) {
        this.hole_suffix = hole_suffix;
    }

    public List<String> getHole_text() {
        return hole_text;
    }

    public void setHole_text(List<String> hole_text) {
        this.hole_text = hole_text;
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
