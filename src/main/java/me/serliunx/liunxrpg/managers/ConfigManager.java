package me.serliunx.liunxrpg.managers;

import me.serliunx.liunxrpg.configs.Config;
import me.serliunx.liunxrpg.configs.files.CommandConfig;
import me.serliunx.liunxrpg.configs.files.LanguageConfig;
import me.serliunx.liunxrpg.configs.files.MainConfig;
import me.serliunx.liunxrpg.enums.YamlFile;

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
