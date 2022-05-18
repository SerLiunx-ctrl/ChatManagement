package me.serliunx.chatmanagement.command.subcommand;

import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.command.subcommand.set.SetFilter;
import me.serliunx.chatmanagement.command.subcommand.set.SetFormat;
import me.serliunx.chatmanagement.enums.Permission;
import me.serliunx.chatmanagement.util.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetCommand extends Command {
    public final Command setFilter, setFormat;

    public SetCommand() {
        super(Collections.singletonList("set"), "a set command", "/chatm set <param.....>",
                Permission.COMMAND_ADMIN_SET.getValue(), false, Duration.ZERO);
        setFilter = new SetFilter();
        setFormat = new SetFormat();

        addChilds(setFilter,setFormat);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(arguments.length == 1){
            for(Command c:getChilds()){
                sender.sendMessage(StringUtils.Color(c.getSyntax() + " &f- " + c.getDescription()));
            }
            return true;
        }
        for(Command c:getChilds()){
            if(arguments[1].equalsIgnoreCase(c.getAliases().get(0))){
                return c.execute(sender, arguments);
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> subs = new ArrayList<>();
        getChilds().forEach(s -> subs.add(s.getAliases().get(0)));
        return subs;
    }
}
