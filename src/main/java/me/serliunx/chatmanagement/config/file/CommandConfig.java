package me.serliunx.chatmanagement.config.file;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.config.Config;

public class CommandConfig extends Config {

    public CommandConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "command.yml");
    }
}
