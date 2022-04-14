package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.configs.Config;
import me.serliunx.chatmanagement.configs.files.*;
import me.serliunx.chatmanagement.enums.YamlFile;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private final Map<String, Config> configs = new HashMap<>();

    public ConfigManager(){
        loadConfigs();
    }

    public void loadConfigs(){
        configs.put(YamlFile.YAML_MAIN.getValue(), new MainConfig());
        configs.put(YamlFile.YAML_LANGUAGE.getValue(), new LanguageConfig());
        configs.put(YamlFile.YAML_COMMAND.getValue(), new CommandConfig());
        configs.put(YamlFile.YAML_FILTER.getValue(), new FilterConfig());
        configs.put(YamlFile.YAML_FORMAT.getValue(), new FormatConfig());
    }

    public Config getByConfigName(String name){
        return configs.get(name);
    }

    public void reloadConfigs(){
        for(Config c: configs.values()){
            c.reloadConfig();
        }
    }
}
