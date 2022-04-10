package me.serliunx.liunxrpg.commands.subcommands;

import me.serliunx.liunxrpg.commands.Command;
import me.serliunx.liunxrpg.commands.subcommands.reload.*;
import me.serliunx.liunxrpg.enums.Permission;
import me.serliunx.liunxrpg.utils.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReloadCommand extends Command {

    public Command allCommand, mainCommand, languageCommand;

    public ReloadCommand() {
        super(Collections.singletonList("reload"),"reload command.","/lr reload <target>", Permission.COMMAND_ADMIN_RELOAD.getValue(), false, Duration.ZERO);

        languageCommand = new LanguageCommand();
        mainCommand = new MainCommand();
        allCommand = new AllCommand();
        addChilds(languageCommand, mainCommand, allCommand);
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
