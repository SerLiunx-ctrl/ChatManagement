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
     * @param values  关键字
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

    /**
     * 保留空参数构造器
     */
    public Filter(){}

    /**
     * 获取过滤器的名称
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置过滤器的名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取跳过该过滤器所需要的权限
     * @return 权限
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置跳过该过滤器所需要的权限
     * @param permission 权限
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取替换字符
     * @return 替换字符
     */
    public String getReplacement() {
        return replacement;
    }

    /**
     * 设置替换字符
     * @param replacement 替换字符
     */
    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    /**
     * 获取过滤器中的所有需要过滤的关键字
     * @return 值
     */
    public List<String> getValues() {
        return values;
    }

    /**
     * 设置过滤器中的所有需要过滤的关键字
     * @param values 值
     */
    public void setValues(List<String> values) {
        this.values = values;
    }

    /**
     * 获取过滤器的状态
     * @return 启用返回真, 否则返回假.
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * 设置过滤器的状态
     * @param enable 状态
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * 获取过滤器中需要过滤的关键字的总数
     * @return 关键字数量
     */
    public int getSize(){
        return values.size();
    }

    /**
     * 给过滤器添加一个需要过滤的关键字
     * @param value 关键字
     * @return 添加成功返回真, 否则返回假
     */
    public boolean addValue(String value){
        if(values.contains(value))
            return false;
        values.add(value);
        return true;
    }

    /**
     * 从过滤器移除一个需要过滤的关键字
     * @param value 关键字
     * @return 移除成功返回真, 否则返回假
     */
    public boolean removeValue(String value){
        if(!values.contains(value))
            return false;
        values.remove(value);
        return true;
    }
}
