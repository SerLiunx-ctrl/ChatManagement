package me.serliunx.chatmanagement.configs.files;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.configs.Config;

public class FilterConfig extends Config {

    public FilterConfig() {
        super(ChatManagement.getInstance().getDataFolder().toString(), "filter.yml");
    }
}
