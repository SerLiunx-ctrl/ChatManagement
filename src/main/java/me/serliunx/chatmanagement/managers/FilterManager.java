package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entities.Filter;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.enums.YamlFile;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.ArrayList;
import java.util.List;

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

    public String filter(User user, String rawText){
        for(Filter filter:filters){
            if(!filter.isEnable()) continue;
            if(filter.getPermission() == null) continue;
            if(filter.getPermission().equals("")) continue;
            if(user.hasPermission(filter.getPermission())) continue;
            for(String value:filter.getValues())
                if(rawText.contains(value))
                    rawText = rawText.replace(value, filter.getReplacement());
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
