package me.serliunx.chatmanagement;

import me.serliunx.chatmanagement.commands.Commands;
import me.serliunx.chatmanagement.listener.PlayerListener;
import me.serliunx.chatmanagement.managers.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatManagement extends JavaPlugin {

    private static ChatManagement instance;
    private Commands commands;
    private ConfigManager configManager;
    private CommandManager commandManager;
    private SQLManager sqlManager;
    private FormatManager formatManager;
    private FilterManager filterManager;
    private UserManager userManager;

    @Override
    public void onEnable() {
        instance = this;

        commands = new Commands();
        configManager = new ConfigManager();
        commandManager = new CommandManager("chatmanagement");
        sqlManager = new SQLManager();
        formatManager = new FormatManager();
        filterManager = new FilterManager();
        userManager = new UserManager();

        //注册监听器.
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
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

    public SQLManager getSqlManager(){
        return sqlManager;
    }

    public FormatManager getFormatManager() {
        return formatManager;
    }

    public FilterManager getFilterManager() {
        return filterManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public static ChatManagement getInstance(){
        return instance;
    }
}
