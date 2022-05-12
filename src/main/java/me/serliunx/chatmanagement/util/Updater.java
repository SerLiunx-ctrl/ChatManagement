package me.serliunx.chatmanagement.util;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.config.Config;
import me.serliunx.chatmanagement.enums.DefaultValue;
import me.serliunx.chatmanagement.enums.YamlFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Updater {

    public final ChatManagement plugin;
    public final List<YamlConfiguration> jarConfigs;

    public Updater() {
        this.plugin = ChatManagement.getInstance();
        jarConfigs = new ArrayList<>();
    }

    public void check(){
        Config mainConfig,commandConfig,filterConfig,formatConfig,langConfig;

        jarConfigs.add(loadConfig(YamlFile.YAML_MAIN.getValue() + ".yml"));
        jarConfigs.add(loadConfig(YamlFile.YAML_COMMAND.getValue() + ".yml"));
        jarConfigs.add(loadConfig(YamlFile.YAML_FILTER.getValue() + ".yml"));
        jarConfigs.add(loadConfig(YamlFile.YAML_FORMAT.getValue() + ".yml"));
        jarConfigs.add(loadConfig(YamlFile.YAML_LANGUAGE.getValue() + ".yml"));

        mainConfig = plugin.getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue());
        commandConfig = plugin.getConfigManager().getByConfigName(YamlFile.YAML_COMMAND.getValue());
        filterConfig = plugin.getConfigManager().getByConfigName(YamlFile.YAML_FILTER.getValue());
        formatConfig = plugin.getConfigManager().getByConfigName(YamlFile.YAML_FORMAT.getValue());
        langConfig = plugin.getConfigManager().getByConfigName(YamlFile.YAML_LANGUAGE.getValue());

        resetConfig(mainConfig, jarConfigs.get(0).getValues(true));
        resetConfig(commandConfig, jarConfigs.get(1).getValues(true));
        resetSection(filterConfig, jarConfigs.get(2));
        resetSection(formatConfig, jarConfigs.get(3));
        resetConfig(langConfig, jarConfigs.get(4).getValues(true));
    }

    private YamlConfiguration loadConfig(String fileName){
        InputStream inputStream = plugin.getResource(fileName);
        if(inputStream == null)return null;
        Reader reader = new InputStreamReader(inputStream);
        return YamlConfiguration.loadConfiguration(reader);
    }

    private void resetConfig(Config config, Map<String,Object> pathAndValues){
        FileConfiguration fileConfiguration = config.getConfiguration();
        //创建数据文件夹中不存在的配置
        for(String path: pathAndValues.keySet()){
            if(!fileConfiguration.contains(path)){
                fileConfiguration.createSection(path);
                fileConfiguration.set(path, pathAndValues.get(path));
                config.save();
            }
        }
    }

    private void resetSection(Config config, YamlConfiguration yamlConfiguration){
        FileConfiguration fileConfiguration = config.getConfiguration();
        Map<String, Object> values = yamlConfiguration.getValues(true);

        for(String section:values.keySet()){
            String defaultValue = DefaultValue.CONFIG_SECTION.getValue();
            if(!section.startsWith(defaultValue)) break;

            for(String path:fileConfiguration.getKeys(false)){
                StringBuilder stringBuilder = new StringBuilder(section);
                stringBuilder.replace(0,defaultValue.length(),path);
                if(!fileConfiguration.contains(stringBuilder.toString())){
                    fileConfiguration.createSection(stringBuilder.toString());
                    fileConfiguration.set(stringBuilder.toString(), values.get(section));
                }

            }
            config.save();
        }
    }

}
