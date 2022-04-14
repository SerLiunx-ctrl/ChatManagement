package me.serliunx.chatmanagement.commands.subcommands;

import me.serliunx.chatmanagement.commands.Command;
import me.serliunx.chatmanagement.commands.subcommands.reload.*;
import me.serliunx.chatmanagement.enums.Permission;
import me.serliunx.chatmanagement.utils.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReloadCommand extends Command {

    public Command allCommand, mainCommand, languageCommand, filterCommand, formatCommand;

    public ReloadCommand() {
        super(Collections.singletonList("reload"),"reload command.","/lr reload <target>", Permission.COMMAND_ADMIN_RELOAD.getValue(), false, Duration.ZERO);

        languageCommand = new LanguageCommand();
        mainCommand = new MainCommand();
        allCommand = new AllCommand();
        filterCommand = new FilterCommand();
        formatCommand = new FormatCommand();
        addChilds(languageCommand, mainCommand, allCommand, filterCommand, formatCommand);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(arguments.length == 1){
            for(Command c:getChilds()){
                sender.sendMessage(StringUtils.Color(c.getSyntax() + " &f- " + c.getDescription()));
            }
            return false;
        }

        for(Command c:getChilds()){
            if(arguments[1].equalsIgnoreCase(c.getAliases().get(0))){
                return c.execute(sender, arguments);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> subs = new ArrayList<>();
        getChilds().forEach(sub -> subs.add(sub.getAliases().get(0)));
        return subs;
    }
}
