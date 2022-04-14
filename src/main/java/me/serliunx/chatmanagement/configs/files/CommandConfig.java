package me.serliunx.chatmanagement.configs.files;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.configs.Config;

public class CommandConfig extends Config {

    public CommandConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "command.yml");
    }
}
