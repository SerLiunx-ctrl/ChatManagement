package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entities.Filter;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.events.player.FiltrationEvent;
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

    public String filter(@NotNull User user, @NotNull String rawText){
        Map<Filter,List<String>> filterListMap = new HashMap<>();
        Player player = user.getPlayer();
        FiltrationEvent filtrationEvent;

        for(Filter filter:filters){
            if(!filter.isEnable()) continue;
            if(filter.getPermission() == null) continue;
            if(filter.getPermission().equals("")) continue;
            if(user.hasPermission(filter.getPermission())) continue;
            filterListMap.put(filter, Collections.emptyList());

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
                if(filtrationEvent.getReplacement() != null)
                    rawText = rawText.replace(value, filtrationEvent.getReplacement());
                else
                    rawText = rawText.replace(value, "*");
            }
        }else {
            for(Filter f:filtrationEvent.getFilterListMap().keySet()){
                for(String value:filtrationEvent.getFilterListMap().get(f)){
                    if(filtrationEvent.getReplacement() != null)
                        rawText = rawText.replace(value, filtrationEvent.getReplacement());
                    else
                        rawText = rawText.replace(value, f.getReplacement());
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
        ChatManagement.getInstance().getLogger().info("loaded " + filters.size() + " filters.");
    }

    private boolean exists(String name){
        if(filters.isEmpty())
            return false;
        for(Filter f: filters){
            if(f.getName().equals(name))
                return true;
        }
        return false;
    }
}
