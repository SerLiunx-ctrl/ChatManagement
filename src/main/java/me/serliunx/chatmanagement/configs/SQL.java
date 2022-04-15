package me.serliunx.chatmanagement.configs;

import me.serliunx.chatmanagement.enums.DriverType;

public final class SQL {

    public DriverType driver = DriverType.SQLITE;
    public String host = "localhost";
    public String database = "ChatManagement";
    public String username = "";
    public String password = "";
    public int port = 3306;
    public boolean useSSL = false;
}
