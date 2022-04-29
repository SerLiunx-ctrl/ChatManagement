package me.serliunx.chatmanagement.event.player;

import me.serliunx.chatmanagement.database.entity.Filter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Map;

public class FiltrationEvent extends CPlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private String replacement;
    private List<String> values;
    private final Map<Filter,List<String>> filterListMap;
    private final String message;

    public FiltrationEvent(final boolean async, @NotNull Player player, @NotNull String message,
                           @NotNull Map<Filter,List<String>> filterListMap){
        super(player, async);
        this.message = message;
        this.filterListMap = filterListMap;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * 获取触发该事件的聊天文字消息
     * <p>
     * 为了避免混乱, 该事件不提供修改聊天文字的方法.
     *
     * @return 聊天文字信息.
     */
    @NotNull
    public String getMessage(){
        return message;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * 返回触发该事件时, 匹配到的所有{@link Filter}及其匹配到的需要替换的字符
     * <p>
     * 匹配到的需要替换的字符不等于该过滤器所有的过滤字符, 只是该事件中所出现的过滤字符.
     */
    @NotNull
    public Map<Filter, List<String>> getFilterListMap() {
        return filterListMap;
    }

    /**
     * 设置替换文本, 如果设置了替换文本, 则默认会忽略过滤器中的替换文本.
     * @param replacement 替换文本
     */
    public void setReplacement(@NotNull String replacement){
        this.replacement = replacement;
    }

    /**
     * 获取手动设置的需要过滤的文字.
     * <p>
     * 必须手动设置 {@link FiltrationEvent#setValues(List)} , 否则将返回 null 值.
     * @return 过滤的文字
     */
    @Nullable
    public List<String> getValues() {
        return values;
    }

    /**
     * 设置该事件中需要过滤的文字.
     * <p>
     * 如果设置了将会忽略{@link FiltrationEvent#getFilterListMap()}中的值及过滤器组.
     * 并且请手动设置替换文本{@link FiltrationEvent#setReplacement(String)}
     * <p>
     * 如果没有设置替换文本, 将会把需要的过滤文字替换成 " * "
     *
     * @param values 文字
     */
    public void setValues(@NotNull List<String> values) {
        this.values = values;
    }

    /**
     * 获取替换文本
     * @return 替换文本
     */
    @Nullable
    public String getReplacement(){
        return replacement;
    }
}
