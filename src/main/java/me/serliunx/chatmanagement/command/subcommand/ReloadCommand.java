package me.serliunx.chatmanagement.command.subcommand;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.command.subcommand.reload.*;
import me.serliunx.chatmanagement.enums.Permission;
import me.serliunx.chatmanagement.util.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReloadCommand extends Command {

    public Command mainCommand, languageCommand, filterCommand, formatCommand, cmdCommand;

    public ReloadCommand() {
        super(Collections.singletonList("reload"),"reload command.","/chatm reload <target>", Permission.COMMAND_ADMIN_RELOAD.getValue(), false, Duration.ZERO);

        languageCommand = new LanguageCommand();
        mainCommand = new MainCommand();
        filterCommand = new FilterCommand();
        formatCommand = new FormatCommand();
        cmdCommand = new CmdCommand();

        addChilds(languageCommand, mainCommand, filterCommand, formatCommand, cmdCommand);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(arguments.length == 1){
            sender.sendMessage(StringUtils.Color("&aAll files reloaded!"));
            return reloadAll();
        }

        for(Command c:getChilds()){
            if(arguments[1].equalsIgnoreCase(c.getAliases().get(0))){
                return c.execute(sender, arguments);
            }
        }

        for(Command c:getChilds()){
            sender.sendMessage(StringUtils.Color(c.getSyntax() + " &f- " + c.getDescription()));
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> subs = new ArrayList<>();
        getChilds().forEach(sub -> subs.add(sub.getAliases().get(0)));
        return subs;
    }

    private boolean reloadAll(){
        ChatManagement.getInstance().getConfigManager().reloadConfigs();
        ChatManagement.getInstance().getCommandManager().reloadCommands();
        ChatManagement.getInstance().getFormatManager().reloadFormats();
        ChatManagement.getInstance().getFilterManager().reloadFilter();
        return true;
    }
}
