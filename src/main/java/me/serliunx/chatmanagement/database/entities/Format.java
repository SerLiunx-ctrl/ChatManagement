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
     * @param name  格式组的名字, 格式组的唯一标识. 不能重复
     * @param permission  权限, 在为玩家选择聊天格式时 这是检查项目之一.
     * @param type  文字显示的方式.{@link ChatType}
     * @param prefix 前缀, 不是权限组插件中的前缀,指的是玩家的聊天文字前的所有内容
     * @param suffix 后缀, 意义同前缀.
     * @param priority 优先级, 当玩家拥有两个格式组的权限时, 会优先选择优先级高的.
     */
    public Format(String name, String permission, ChatType type, String prefix, String suffix, int priority) {
        this.name = name;
        this.permission = permission;
        this.chatType = type;
        this.prefix = prefix;
        this.suffix = suffix;
        this.priority = priority;
    }

    /**
     * 保留空构造器
     * 使用前请设置必要的属性.
     */
    public Format(){};

    /**
     * 获取格式组的名称
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置格式组的名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取格式组的权限
     * @return 权限
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置格式组的权限
     * @param permission 权限
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取格式组的文字显示方式{@link ChatType}
     * @return 显示方式
     */
    public ChatType getChatType() {
        return chatType;
    }

    /**
     * 设置格式组的文字显示方式
     * @param chatType 文字显示方式 {@link ChatType}
     */
    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    /**
     * 获取格式组的前缀
     * @return 前缀
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * 设置格式组的前缀
     * @param prefix 前缀
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 获取格式组的后缀
     * @return 后缀
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * 设置格式组的后缀
     * @param suffix 后缀
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * 获取该格式组的优先级
     * @return 优先级
     */
    public int getPriority() {
        return priority;
    }

    /**
     * 设置格式组的优先级, 必须大于等于 0
     * 如果设置值小于 0, 会自动取反
     * @param priority 优先级
     */
    public void setPriority(int priority) {
        if(priority < 0)
            this.priority = priority * -1;
        else
            this.priority = priority;
    }

    /**
     * 获取前缀的悬浮文字
     * @return 前缀悬浮文字
     */
    public List<String> getPrefixHolo() {
        return prefixHolo;
    }

    /**
     * 设置前缀的悬浮文字
     * @param prefixHolo 悬浮文字
     */
    public void setPrefixHolo(List<String> prefixHolo) {
        this.prefixHolo = prefixHolo;
    }

    /**
     * 获取后缀的悬浮文字
     * @return 悬浮文字
     */
    public List<String> getSuffixHolo() {
        return suffixHolo;
    }

    /**
     * 设置后缀的悬浮文字
     * @param suffixHolo 悬浮文字
     */
    public void setSuffixHolo(List<String> suffixHolo) {
        this.suffixHolo = suffixHolo;
    }

    /**
     * 获取文字内容悬浮文字
     * @return 悬浮文字
     */
    public List<String> getTextHolo() {
        return textHolo;
    }

    /**
     * 设置文字内容的悬浮文字
     * @param textHolo 悬浮文字
     */
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
