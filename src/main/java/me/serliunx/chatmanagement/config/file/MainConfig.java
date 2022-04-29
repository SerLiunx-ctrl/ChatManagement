package me.serliunx.chatmanagement.config.file;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.config.Config;

public class MainConfig extends Config {

    public MainConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "config.yml");
    }
}
