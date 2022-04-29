package me.serliunx.chatmanagement.config.file;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.config.Config;

public class LanguageConfig extends Config {

    public LanguageConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "lang.yml");
    }
}
