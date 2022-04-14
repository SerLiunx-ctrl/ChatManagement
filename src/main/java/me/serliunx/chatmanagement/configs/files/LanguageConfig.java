package me.serliunx.chatmanagement.configs.files;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.configs.Config;

public class LanguageConfig extends Config {

    public LanguageConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "lang.yml");
    }
}
