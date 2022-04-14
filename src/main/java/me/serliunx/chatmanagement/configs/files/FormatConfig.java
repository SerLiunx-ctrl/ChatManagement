package me.serliunx.chatmanagement.configs.files;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.configs.Config;

public class FormatConfig extends Config {

    public FormatConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "format.yml");
    }
}
