package me.serliunx.chatmanagement;

import me.serliunx.chatmanagement.commands.Commands;
import me.serliunx.chatmanagement.managers.CommandManager;
import me.serliunx.chatmanagement.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatManagement extends JavaPlugin {

    private static ChatManagement instance;
    private ConfigManager configManager;
    private CommandManager commandManager;
    private Commands commands;

    @Override
    public void onEnable() {
        instance = this;

        commands = new Commands();
        configManager = new ConfigManager();
        commandManager = new CommandManager("chatmanagement");
    }

    @Override
    public void onDisable() {

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

    public static ChatManagement getInstance(){
        return instance;
    }
}
