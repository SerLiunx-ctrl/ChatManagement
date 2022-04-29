package me.serliunx.chatmanagement.config.file;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.config.Config;

public class FormatConfig extends Config {

    public FormatConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "format.yml");
    }
}
