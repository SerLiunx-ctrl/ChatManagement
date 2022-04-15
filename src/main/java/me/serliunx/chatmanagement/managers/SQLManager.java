package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.configs.SQL;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLManager {

    private Connection connection;

    public void init(SQL sqlConfig) throws SQLException {
        String databaseURL =getDatabaseURL(sqlConfig);
        connection = DriverManager.getConnection(databaseURL);
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected(){
        return (connection != null);
    }

    public void disconnect(){
        if(isConnected()){
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    private @NotNull String getDatabaseURL(SQL sqlConfig){
        return switch (sqlConfig.driver) {
            case MYSQL -> "jdbc:" + sqlConfig.driver.name().toLowerCase() + "://" + sqlConfig.host + ":" + sqlConfig.port
                    + "/" + sqlConfig.database + "?useSSL=" + sqlConfig.useSSL + "&autoReconnect=true";
            case SQLITE -> "jdbc:sqlite:" + new File(ChatManagement.getInstance().getDataFolder(), sqlConfig.database + ".db");
        };
    }
}
