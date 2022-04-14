package me.serliunx.chatmanagement.configs.files;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.configs.Config;

public class MainConfig extends Config {

    public MainConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "config.yml");
    }
}
