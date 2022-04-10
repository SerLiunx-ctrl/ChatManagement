package me.serliunx.liunxrpg.configs.files;

import me.serliunx.liunxrpg.LiunxRPG;
import me.serliunx.liunxrpg.configs.Config;

public class LanguageConfig extends Config {

    public LanguageConfig() {
        super(LiunxRPG.getInstance().getDataFolder().toString(), "lang.yml");
    }
}
