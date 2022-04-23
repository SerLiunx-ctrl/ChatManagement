package me.serliunx.chatmanagement.configs;

import me.serliunx.chatmanagement.enums.DriverType;

public final class SQL {

    public DriverType driver = DriverType.SQLITE;
    public String host = "localhost";
    public String playerTable = "CM_PLAYER";
    public String database = "database";
    public String groupTable = "CM_GROUP";
    public String username = "";
    public String password = "";
    public int port = 3306;
    public boolean useSSL = false;
}
