package me.serliunx.chatmanagement.command.subcommand.set;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.database.entity.Filter;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetFilter extends Command {
    private final ChatManagement instance;

    public SetFilter() {
        super(Collections.singletonList("filter"), "filter settings", "/chatm set filter", "",
                false, Duration.ZERO);
        instance = ChatManagement.getInstance();
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(!getFilterNames().contains(arguments[2])){
            sender.sendMessage("cannot find the filter: " + arguments[2]);
            return false;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return getFilterNames();
    }

    private List<String> getFilterNames(){
        List<String> filterNames = new ArrayList<>();
        for(Filter filter:instance.getFilterManager().getFilters())
            filterNames.add(filter.getName());
        return filterNames;
    }
}
