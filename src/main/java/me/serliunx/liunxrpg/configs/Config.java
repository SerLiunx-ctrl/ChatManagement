package me.serliunx.liunxrpg.configs;

import me.serliunx.liunxrpg.LiunxRPG;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public abstract class Config {

    private final String fileName;
    private final String pathName;
    private File file;
    private FileConfiguration configuration;

    public Config(String pathName, String fileName){
        this.pathName = pathName;
        this.fileName = fileName;
        saveDefaultConfig();
    }

    public boolean reloadConfig(){
        try{
            file = new File(pathName, fileName);
            configuration = YamlConfiguration.loadConfiguration(file);
            return true;
        }catch(Exception e){
            LiunxRPG.getInstance().getLogger().warning(e.toString());
        }
        return false;
    }

    private void saveDefaultConfig() {
        file = new File(pathName,fileName);
        if(!file.exists())
            LiunxRPG.getInstance().saveResource(fileName,false);
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig(){};

    public FileConfiguration getConfiguration(){
        return configuration;
    }
}
