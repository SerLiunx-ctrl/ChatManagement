package me.serliunx.liunxrpg.configs.files;

import me.serliunx.liunxrpg.LiunxRPG;
import me.serliunx.liunxrpg.configs.Config;

public class CommandConfig extends Config {

    public CommandConfig() {
        super(LiunxRPG.getInstance().getDataFolder().toString(), "command.yml");
    }
}
