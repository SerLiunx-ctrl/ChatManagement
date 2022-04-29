package me.serliunx.chatmanagement.config;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.enums.DriverType;
import me.serliunx.chatmanagement.enums.YamlFile;
import org.bukkit.configuration.file.FileConfiguration;

public final class SQL {

    public DriverType driver;
    public String host;
    public String playerTable;
    public String database;
    public String groupTable;
    public String username;
    public String password;
    public int port;
    public boolean useSSL = false;

    public SQL(){
        FileConfiguration sqlConfig = ChatManagement.getInstance().getConfigManager()
                .getByConfigName(YamlFile.YAML_MAIN.getValue()).getConfiguration();

        driver = DriverType.valueOf(sqlConfig.getString("sql.driver", "SQLITE"));
        host = sqlConfig.getString("sql.host", "localhost");
        playerTable = sqlConfig.getString("sql.playerTable", "CM_PLAYER");
        database = sqlConfig.getString("sql.database", "database");
        groupTable = sqlConfig.getString("sql.groupTable", "CM_GROUP");
        username = sqlConfig.getString("sql.username", "");
        password = sqlConfig.getString("sql.password", "");
        port = sqlConfig.getInt("sql.port", 3306);
    }
}
