package me.serliunx.chatmanagement;

import me.serliunx.chatmanagement.commands.Commands;
import me.serliunx.chatmanagement.configs.SQL;
import me.serliunx.chatmanagement.listener.PlayerListener;
import me.serliunx.chatmanagement.managers.*;
import me.serliunx.chatmanagement.placeholders.Placeholders;
import me.serliunx.chatmanagement.utils.Language;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.SQLException;

public final class ChatManagement extends JavaPlugin {

    private boolean usePapi;

    private static ChatManagement instance;
    private Commands commands;
    private ConfigManager configManager;
    private CommandManager commandManager;
    private SQL sql;
    private SQLManager sqlManager;
    private FormatManager formatManager;
    private FilterManager filterManager;
    private UserManager userManager;
    private ControllerManager controllerManager;
    private Placeholders placeholders;
    private Language language;

    @Override
    public void onEnable() {
        instance = this;

        commands = new Commands();
        configManager = new ConfigManager();
        commandManager = new CommandManager("chatmanagement");
        //载入数据库配置
        sql = new SQL();

        sqlManager = new SQLManager();
        language = new Language();

        try{
            sqlManager.init(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }

        formatManager = new FormatManager();
        filterManager = new FilterManager();
        userManager = new UserManager();
        controllerManager = new ControllerManager();
        placeholders = new Placeholders();

        //注册监听器.
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        //检测PlaceholderAPI
        usePapi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

    }

    @Override
    public void onDisable() {
        if(sqlManager.isConnected()){
            sqlManager.disconnect();
        }
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

    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    public Placeholders getPlaceholders() {
        return placeholders;
    }

    public Language getLanguage() {
        return language;
    }

    public SQL getSql() {
        return sql;
    }

    public boolean isUsePapi() {
        return usePapi;
    }

    public static ChatManagement getInstance(){
        return instance;
    }

}
