package me.serliunx.chatmanagement.util;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.enums.YamlFile;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Updater {

    public final ChatManagement plugin;
    public final List<Map<String,Object>> configs;

    public Updater() {
        this.plugin = ChatManagement.getInstance();
        configs = new ArrayList<>();
    }

    public void check(){
        configs.add(loadConfig(YamlFile.YAML_MAIN.getValue() + ".yml"));
        configs.add(loadConfig(YamlFile.YAML_COMMAND.getValue() + ".yml"));
        configs.add(loadConfig(YamlFile.YAML_FILTER.getValue() + ".yml"));
        configs.add(loadConfig(YamlFile.YAML_FORMAT.getValue() + ".yml"));
        configs.add(loadConfig(YamlFile.YAML_LANGUAGE.getValue() + ".yml"));
    }

    private Map<String, Object> loadConfig(String fileName){
        InputStream inputStream = plugin.getResource(fileName);
        if(inputStream == null)return null;

        Reader reader = new InputStreamReader(inputStream);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(reader);

        return configuration.getValues(true);
    }
}
