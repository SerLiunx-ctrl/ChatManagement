package me.serliunx.chatmanagement.manager;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entity.Filter;
import me.serliunx.chatmanagement.database.entity.User;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.api.event.player.FiltrationEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.*;

public class FilterManager {

    private final List<Filter> filters;
    private FileConfiguration filterFile;

    public FilterManager(){
        filters = new ArrayList<>();
        reloadFilter();
    }
    
    public void reloadFilter(){
        filterFile = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_FILTER.getValue())
                .getConfiguration();
        loadFromFile();
    }

    public void addFilter(Filter filter){
        if(!filters.contains(filter))
            filters.add(filter);
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public String filter(@NotNull Player player, @NotNull String rawText){
        return filter(ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId()), rawText);
    }

    /**
     * 使用本插件中的过滤功能来过滤文字<p>
     *<li>将过滤文字中所有符合已启用的过滤器所包含的值
     *<li>不会触发 {@link me.serliunx.chatmanagement.api.event.player.FiltrationEvent}
     * @param rawText 需要过滤的文本
     * @return 过滤后的文字
     */
    public String filter(String rawText){
        for(Filter f: filters){
            if(!f.isEnable()) continue;
            for(String value: f.getValues()){
                rawText = rawText.replace(value,f.getReplacement() == null ||
                        f.getReplacement().equals("") ? "*":f.getReplacement());
            }
        }
        return rawText;
    }

    public String filter(@NotNull User user, @NotNull String rawText){
        Map<Filter,List<String>> filterListMap = new HashMap<>();
        Player player = user.getPlayer();
        FiltrationEvent filtrationEvent;

        for(Filter filter:filters){
            if(!filter.isEnable()) continue;
            if(filter.getPermission() == null) continue;
            if(filter.getPermission().equals("")) continue;
            if(player.hasPermission(filter.getPermission())) continue;
            filterListMap.put(filter, new ArrayList<>());

            for(String value:filter.getValues()){
                if(rawText.contains(value)){
                    filterListMap.get(filter).add(value);
                }
            }
        }

        filtrationEvent = new FiltrationEvent(true, player, rawText, filterListMap);
        Bukkit.getPluginManager().callEvent(filtrationEvent);
        if(filtrationEvent.isCancelled()) return rawText;

        if(filtrationEvent.getValues() != null){
            for(String value: filtrationEvent.getValues()){
                rawText = rawText.replace(value, filtrationEvent.getReplacement() == null ? "*" :
                        filtrationEvent.getReplacement());
            }
        }else {
            for(Filter f:filtrationEvent.getFilterListMap().keySet()){
                for(String value:filtrationEvent.getFilterListMap().get(f)){
                    rawText = rawText.replace(value, filtrationEvent.getReplacement() == null ? "*" :
                            filtrationEvent.getReplacement());
                }
            }
        }

        return rawText;
    }

    private void loadFromFile(){
        filters.clear();
        for(String key:filterFile.getKeys(false)){
            if(exists(key)) continue;
            addFilter(new Filter(key, filterFile.getString(key + ".skip-permission"), filterFile
                    .getString(key + ".replace-by"), filterFile.getBoolean(key + ".enable"),
                    filterFile.getStringList(key + ".values")));
        }
        ChatManagement.getInstance().getLogger().info(ChatManagement.getInstance().getLanguage()
                .getSingleLine("filter_loaded").replace("{0}", String.valueOf(filters.size())));
    }

    private boolean exists(String name){
        if(filters.isEmpty()) return false;
        for(Filter f: filters){
            if(f.getName().equals(name)) return true;
        }
        return false;
    }
}
