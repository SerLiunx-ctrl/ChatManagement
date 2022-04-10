package me.serliunx.liunxrpg;

import me.serliunx.liunxrpg.commands.Commands;
import me.serliunx.liunxrpg.managers.CommandManager;
import me.serliunx.liunxrpg.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LiunxRPG extends JavaPlugin {

    private static LiunxRPG instance;

    private ConfigManager configManager;
    private CommandManager commandManager;
    private Commands commands;

    @Override
    public void onEnable() {
        instance = this;

        commands = new Commands();
        configManager = new ConfigManager();
        commandManager = new CommandManager("liunxrpg");
    }

    @Override
    public void onDisable() {

    }

    private void register(){

    }

    public CommandManager getCommandManager(){
        return commandManager;
    }

    public ConfigManager getConfigManager(){
        return configManager;
    }

    public Commands getCommands(){
        return commands;
    }

    public static LiunxRPG getInstance(){
        return instance;
    }
}
