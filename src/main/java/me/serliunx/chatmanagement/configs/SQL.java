package me.serliunx.chatmanagement.configs;

public final class SQL {

    public Driver driver = Driver.SQLITE;
    public String host = "localhost";
    public String database = "ChatManagement";
    public String username = "";
    public String password = "";
    public int port = 3306;
    public boolean useSSL = false;

    public enum Driver{
        MYSQL,
        SQLITE
    }
}
