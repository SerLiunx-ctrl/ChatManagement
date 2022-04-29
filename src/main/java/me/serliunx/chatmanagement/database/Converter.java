package me.serliunx.chatmanagement.database;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.config.SQL;
import me.serliunx.chatmanagement.database.entity.User;
import me.serliunx.chatmanagement.enums.DriverType;
import me.serliunx.chatmanagement.manager.SQLManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public final class Converter {

    private final SQL sql;
    private final SQLManager sqlManager;
    private Connection connection;
    private CommandSender commandSender;

    public Converter(SQL sql, SQLManager sqlManager) {
        this.sql = sql;
        this.sqlManager = sqlManager;
    }

    public boolean convert(DriverType targetDriverType, CommandSender commandSender){
        if(targetDriverType == sql.driver)
            return false;

        this.commandSender = commandSender;
        if(targetDriverType == DriverType.MYSQL){
            return convertToMysql();
        }else{
            return convertToSQLite();
        }
    }

    public boolean convertToMysql(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql" + "://" + sql.host + ":" + sql.port + "/" + sql.database + "?useSSL=" + sql.useSSL + "&autoReconnect=true",
                    sql.username, sql.password);
            createTable();
            uploadPlayers(ChatManagement.getInstance().getUserManager().getUsers(),connection);
            connection.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean convertToSQLite(){
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + new File(ChatManagement.getInstance().getDataFolder(),
                    sql.database + ".db"));
            createTable();
            uploadPlayers(ChatManagement.getInstance().getUserManager().getUsers(),connection);
            connection.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private void uploadPlayers(List<User> users, Connection connection) throws SQLException{
        int i = 0;
        for(User user:users){
            sqlManager.createPlayer(user, connection);
            if(!sqlManager.exists(user, connection))
                i++;
        }

        ChatManagement.getInstance().getLogger().info(i + " player's data has been converted!");
        if(commandSender instanceof Player)
            commandSender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("sql_converter_done")
                    .replace("{0}",String.valueOf(i)));
    }

    private void createTable() throws SQLException{
        PreparedStatement ps;
        ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "+ sql.playerTable +" (UUID VARCHAR(100), PREFIX VARCHAR(100), SUFFIX VARCHAR(100), CHAT_TYPE VARCHAR(100), " +
                "PRIVATE_MESSAGE VARCHAR(20), PREFIX_HOLO VARCHAR(500), SUFFIX_HOLO VARCHAR(500), TEXT_HOLO VARCHAR(500), PRIMARY KEY(UUID))");
        ps.executeUpdate();
    }
}
