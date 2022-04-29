package me.serliunx.chatmanagement.config.file;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.config.Config;

public class FilterConfig extends Config {

    public FilterConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "filter.yml");
    }
}
