package me.serliunx.chatmanagement.database.entities;

import java.util.List;

/**
 * 过滤器类，用于过滤玩家的各种文字
 */
public final class Filter {

    private String name;
    private String permission;
    private String replacement;
    private List<String> values;
    private boolean enable;

    /**
     * 全参数构造器
     *
     * @param name  过滤器名名称
     * @param permission  跳过该过滤器所需要的权限
     * @param replacement  替换字符
     * @param values  值
     * @param enable  是否启用
     */
    public Filter(String name, String permission, String replacement, List<String> values, boolean enable) {
        this.name = name;
        this.enable = enable;
        this.permission = permission;
        this.replacement = replacement;
        this.values = values;
    }

    /**
     * 部分参数构造器, values 默认为null, 有需要请手动赋值.
     *
     * @param name  过滤器名名称
     * @param enable  是否启用
     * @param permission  跳过该过滤器所需要的权限
     * @param replacement  替换字符
     */
    public Filter(String name, String permission, String replacement, boolean enable) {
        this.name = name;
        this.permission = permission;
        this.replacement = replacement;
        this.enable = enable;
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

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
