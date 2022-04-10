package me.serliunx.liunxrpg.configs.files;

import me.serliunx.liunxrpg.LiunxRPG;
import me.serliunx.liunxrpg.configs.Config;

public class MainConfig extends Config {

    public MainConfig() {
        super(LiunxRPG.getInstance().getDataFolder().toString(), "config.yml");
    }
}
