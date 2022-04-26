package me.serliunx.chatmanagement.configs;

import me.serliunx.chatmanagement.enums.DriverType;

public final class SQL {

    public DriverType driver = DriverType.MYSQL;
    public String host = "localhost";
    public String playerTable = "CM_PLAYER";
    public String database = "serliunx";
    public String groupTable = "CM_GROUP";
    public String username = "root";
    public String password = "2636257370";
    public int port = 3306;
    public boolean useSSL = false;
}
