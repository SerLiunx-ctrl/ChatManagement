package me.serliunx.chatmanagement;

import me.serliunx.chatmanagement.command.Commands;
import me.serliunx.chatmanagement.config.SQL;
import me.serliunx.chatmanagement.database.Converter;
import me.serliunx.chatmanagement.database.entity.User;
import me.serliunx.chatmanagement.listener.PlayerListener;
import me.serliunx.chatmanagement.manager.*;
import me.serliunx.chatmanagement.placeholder.ClipPlaceholderAPI;
import me.serliunx.chatmanagement.placeholder.Placeholders;
import me.serliunx.chatmanagement.util.Language;
import me.serliunx.chatmanagement.util.Updater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.SQLException;

public final class ChatManagement extends JavaPlugin {

    private boolean usePapi = false;
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
    private Converter converter;
    private Updater updater;

    @Override
    public void onLoad(){
        instance = this;
    }

    @Override
    public void onEnable() {
        commands = new Commands();
        configManager = new ConfigManager();
        updater = new Updater();

        //检测并更新config
        updater.check();

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
        converter = new Converter(sql, sqlManager);

        //注册监听器.
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            usePapi = true;
            new ClipPlaceholderAPI().register();
        }

        //热加载更新玩家数据
        Bukkit.getOnlinePlayers().forEach(p -> getUserManager().addUser(new User(p.getUniqueId())));

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

    public Converter getConverter() {return converter; }

    public SQL getSql() {
        return sql;
    }

    public Updater getUpdater(){
        return updater;
    }

    public boolean isUsePapi() {
        return usePapi;
    }

    public static ChatManagement getInstance(){
        return instance;
    }

}
