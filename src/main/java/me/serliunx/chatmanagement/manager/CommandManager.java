package me.serliunx.chatmanagement.manager;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.command.Commands;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.util.StringUtils;
import me.serliunx.chatmanagement.util.TimeUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final List<Command> commands = new ArrayList<>();
    private final FileConfiguration commandConfiguration;

    public CommandManager(String command){
        PluginCommand pluginCommand = ChatManagement.getInstance().getCommand(command);
        if(pluginCommand != null){
            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);
        }

        commandConfiguration = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_COMMAND.getValue()).getConfiguration();
        registerCommands();
    }

    private void registerCommands(){
        Commands commands = ChatManagement.getInstance().getCommands();
        for (Field field : commands.getClass().getFields()) {
            try {
                Command command = (Command) field.get(commands);
                registerCommand(command);
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void registerCommand(Command command) {
        if (command.isEnabled()) {
            int index = Collections.binarySearch(commands, command, Comparator.comparing(cmd -> cmd.getAliases().get(0)));
            command = loadCommandLanguage(command);
            commands.add(index < 0 ? -(index + 1) : index, command);
        }
    }

    public Command loadCommandLanguage(Command command){
        for(String key:commandConfiguration.getKeys(false)){
            if(command.getAliases().get(0).equalsIgnoreCase(key)){
                command.setDescription(commandConfiguration.getString(key + ".description",command.getDescription()));
                command.setEnabled(commandConfiguration.getBoolean(key + ".enable",true));
                command.setSyntax(commandConfiguration.getString(key + ".syntax",command.getSyntax()));

                if(!command.getChilds().isEmpty()){
                    for(Command cd:command.getChilds()){
                        cd = loadCommandLanguage(cd, key + ".childs." + cd.getAliases().get(0));
                    }
                }

                return command;
            }
        }
        return command;
    }

    public Command loadCommandLanguage(Command cd, String path){
        cd.setDescription(commandConfiguration.getString(path + ".description", cd.getDescription()));
        cd.setEnabled(commandConfiguration.getBoolean(path + ".enable",true));
        cd.setSyntax(commandConfiguration.getString(path + ".syntax",cd.getSyntax()));
        if(!cd.getChilds().isEmpty()){
            for(Command c:cd.getChilds()){
                c = loadCommandLanguage(c,path + ".childs." + c.getAliases().get(0));
            }
        }

        return cd;
    }

    public void unregisterCommand(Command command) {
        commands.remove(command);
    }

    public void reloadCommands(){
        commands.clear();
        registerCommands();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command cmd, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0){
            ChatManagement.getInstance().getCommands().helpCommand.execute(commandSender,new String[]{});
            return false;
        }
        for(Command command : commands){
            if(!(command.getAliases().contains(args[0])))
                continue;

            Command executingCommand = findExecutingCommand(command, args);
            if (executionBlocked(executingCommand, commandSender)) {
                return false;
            }

            boolean success = executingCommand.execute(commandSender, args);
            if (success) executingCommand.getCooldownProvider().applyCooldown(commandSender);
            return true;
        }

        //未找到该指令
        commandSender.sendMessage(StringUtils.Color("&c what ? haven't this command."));
        return false;
    }

    /**
     *
     * 检查是否能顺利执行该指令.
     *
     * @param command 指令.
     * @param commandSender 执行者.
     * @return 返回真如果可以执行, 否则返回假.
     */
    private boolean executionBlocked(Command command, @NotNull CommandSender commandSender) {
        if (command.isForPlayer() && !(commandSender instanceof Player)) {
            commandSender.sendMessage(StringUtils.Color("&c only for player!"));
            return true;
        }

        if (!hasPermissions(commandSender, command)) {
            commandSender.sendMessage(StringUtils.Color("&c no permission."));
            return true;
        }

        CooldownProvider<CommandSender> cooldownProvider = command.getCooldownProvider();

        if (commandSender instanceof Player && cooldownProvider.isOnCooldown(commandSender)) {
            Duration remainingTime = cooldownProvider.getRemainingTime(commandSender);
            String formattedTime = TimeUtils.formatDuration("{seconds}", remainingTime);
            commandSender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("in_cooldown")
                    .replace("{seconds}", formattedTime));
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command cmd, @NotNull String label, @NotNull String[] args) {

        //当位于第一个参数时, 返回所有指令.
        if (args.length == 1) {
            ArrayList<String> result = new ArrayList<>();
            for (Command command : commands) {
                for (String alias : command.getAliases()) {
                    if (alias.toLowerCase().startsWith(args[0].toLowerCase()) && hasPermissions(commandSender, command)) {
                        result.add(alias);
                    }
                }
            }

            return result;
        }

        //返回指令的子命令.
        for (Command command : commands) {
            if (command.getAliases().contains(args[0])) {
                Command executingCommand = findExecutingCommand(command, args);
                if (hasPermissions(commandSender, executingCommand)) {
                    List<String> tabCompletion = new ArrayList<>(executingCommand.onTabComplete(commandSender, cmd, label, args));

                    executingCommand.getChilds().stream()
                            .filter(subCommand -> hasPermissions(commandSender, subCommand))
                            .map(subCommand -> subCommand.getAliases().get(0))
                            .forEach(tabCompletion::add);

                    return filterTabCompletionResults(tabCompletion, args);
                }
            }
        }

        //如果上述逻辑都没执行到则返回一个空列表, 以防止默认返回玩家列表.
        return Collections.emptyList();
    }

    /**
     *
     * 检测指令执行者是否有权限.
     *
     * @param command 指令.
     * @param commandSender 执行者.
     * @return 返回真如果有权限, 否则返回假.
     */
    private boolean hasPermissions(@NotNull CommandSender commandSender, Command command) {
        return commandSender.hasPermission(command.getPermission())
                || command.getPermission().equalsIgnoreCase("")
                || command.getPermission().equalsIgnoreCase("liunxrpg.");
    }

    private Command findExecutingCommand(Command baseCommand, String[] args) {
        Command executingCommand = baseCommand;

        for (int currentArgument = 1; currentArgument < args.length; currentArgument++) {
            Optional<Command> child = executingCommand.getChildByName(args[currentArgument]);
            if (!child.isPresent()) break;

            executingCommand = child.get();
        }

        return executingCommand;
    }

    private List<String> filterTabCompletionResults(List<String> tabCompletion, String[] arguments) {
        return tabCompletion.stream()
                .filter(completion -> completion.toLowerCase().contains(arguments[arguments.length - 1].toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Command> getCommands() {
        return commands;
    }
}