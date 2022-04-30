package me.serliunx.chatmanagement.command.subcommand;

import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.command.subcommand.list.FilterList;
import me.serliunx.chatmanagement.command.subcommand.list.FormatList;
import me.serliunx.chatmanagement.enums.Permission;
import me.serliunx.chatmanagement.util.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListCommand extends Command {

    public Command filterList, formatList;

    public ListCommand() {
        super(Collections.singletonList("list"), "list information all you need", "/chatm list", Permission.COMMAND_ADMIN_LIST.getValue(), false, Duration.ofSeconds(2));
        formatList = new FormatList();
        filterList = new FilterList();
        addChilds(filterList,formatList);
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
