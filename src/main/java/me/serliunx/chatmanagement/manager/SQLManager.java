package me.serliunx.chatmanagement.manager;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.config.SQL;
import me.serliunx.chatmanagement.database.entity.User;
import me.serliunx.chatmanagement.enums.ChatType;
import me.serliunx.chatmanagement.enums.DriverType;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.sql.*;
import java.util.*;

public final class SQLManager {

    private Connection connection;

    public void init(SQL sqlConfig) throws SQLException {
        String databaseURL =getDatabaseURL(sqlConfig);
        if(sqlConfig.driver == DriverType.MYSQL)
            connection = DriverManager.getConnection(databaseURL,sqlConfig.username, sqlConfig.password);
        else
            connection = DriverManager.getConnection(databaseURL);

        createTable();
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
        switch (sqlConfig.driver){
            case MYSQL:
                return "jdbc:" + sqlConfig.driver.name().toLowerCase() + "://" + sqlConfig.host + ":" + sqlConfig.port
                        + "/" + sqlConfig.database + "?useSSL=" + sqlConfig.useSSL + "&autoReconnect=true";
            case SQLITE:
                return "jdbc:sqlite:" + new File(ChatManagement.getInstance().getDataFolder(), sqlConfig.database + ".db");
            default:
                throw new RuntimeException("unsupported sql driver!");
        }
    }

    private void createTable() throws SQLException{
        PreparedStatement ps;
        ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + ChatManagement.getInstance().getSql().playerTable +" (UUID VARCHAR(100), PREFIX VARCHAR(100), SUFFIX VARCHAR(100), CHAT_TYPE VARCHAR(100), " +
                "PRIVATE_MESSAGE VARCHAR(20), PREFIX_HOLO VARCHAR(500), SUFFIX_HOLO VARCHAR(500), TEXT_HOLO VARCHAR(500), CHAT_STATUS VARCHAR(20), PRIMARY KEY(UUID))");
        ps.executeUpdate();
    }

    /**
     * ???????????????????????????????????????, ????????????????????????
     * @param user ??????
     */
    public void createPlayer(User user) throws SQLException{
        if(!exists(user)){
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO " + ChatManagement.getInstance()
                    .getSql().playerTable + " VALUES (?,?,?,?,?,?,?,?,?)");
            createPlayerPs(ps, user);
        }
    }

    /**
     * ???????????????????????????????????????, ????????????????????????
     * @param user ??????
     * @param connection ???????????????
     */
    public void createPlayer(User user, Connection connection) throws SQLException{
        if(!exists(user, connection)){
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " + ChatManagement.getInstance()
                    .getSql().playerTable + " VALUES (?,?,?,?,?,?,?,?,?)");
            createPlayerPs(ps, user);
        }
    }

    private void createPlayerPs(PreparedStatement ps, User user) throws SQLException{
        ps.setString(1, user.getUuid().toString());
        ps.setString(2, user.getPrefix());
        ps.setString(3, user.getSuffix());
        ps.setString(4, user.getChatType().toString());
        ps.setString(5, String.valueOf(user.isPmStatus()));
        ps.setString(6,null);
        ps.setString(7,null);
        ps.setString(8,null);
        ps.setString(9, String.valueOf(user.getChatStatus()));
        ps.executeUpdate();
    }

    public Map<UUID, User> loadPlayers(){
        Map<UUID, User> userMap = new HashMap<>();
        try{
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM " + ChatManagement.getInstance().getSql().playerTable);
            ResultSet rs = ps.executeQuery();
            List<String> prefix_holo = new ArrayList<>();
            List<String> suffix_holo = new ArrayList<>();
            List<String> text_holo = new ArrayList<>();

            while (rs.next()){
                if(rs.getString(6) != null){
                    String[] s = rs.getString(6).split("\\\\n", -1);
                    prefix_holo = Arrays.asList(s);
                }
                if(rs.getString(7) != null){
                    String[] s = rs.getString(7).split("\\\\n", -1);
                    suffix_holo = Arrays.asList(s);
                }
                if(rs.getString(8) != null){
                    String[] s = rs.getString(8).split("\\\\n", -1);
                    text_holo = Arrays.asList(s);
                }
                User user = new User(rs.getString(2), rs.getString(3), prefix_holo, suffix_holo, text_holo,
                        ChatType.valueOf(rs.getString(4)), UUID.fromString(rs.getString(1)),
                        rs.getString(5).equals("true"), rs.getString(9).equals("true"));
                userMap.put(UUID.fromString(rs.getString(1)), user);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return userMap;
    }

    public void updatePlayer(User user, String attribute, String context) throws SQLException{
        PreparedStatement ps = getConnection().prepareStatement("UPDATE " + ChatManagement.getInstance().getSql().playerTable +
                " SET " + attribute + "=?" + " WHERE " + " UUID=?");
        ps.setString(2,user.getUuid().toString());
        if(attribute.equals("PREFIX_HOLO") || attribute.equals("SUFFIX_HOLO") || attribute.equals("TEXT_HOLO")){
            ps.setString(1, context.equals("null") ? null : context);
        }else{
            ps.setString(1, context);
        }

        ps.executeUpdate();
    }

    /**
     * ???????????????????????????????????????
     * @param user ??????
     * @return ???????????????,???????????????
     */
    public boolean exists(@NotNull User user){
        try{
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM " + ChatManagement.getInstance()
                    .getSql().playerTable + " WHERE UUID=?");
            ps.setString(1,user.getUuid().toString());
            ResultSet results = ps.executeQuery();
            return results.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ???????????????????????????????????????
     * @param user ??????
     * @param connection ???????????????
     * @return ???????????????,???????????????
     */
    public boolean exists(@NotNull User user, @NotNull Connection connection){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + ChatManagement.getInstance()
                    .getSql().playerTable + " WHERE UUID=?");
            ps.setString(1,user.getUuid().toString());
            ResultSet results = ps.executeQuery();
            return results.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
