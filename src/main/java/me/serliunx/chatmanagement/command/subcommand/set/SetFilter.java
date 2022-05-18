package me.serliunx.chatmanagement.command.subcommand.set;

import me.serliunx.chatmanagement.command.Command;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class SetFilter extends Command {

    public SetFilter() {
        super(Collections.singletonList("filter"), "filter settings", "/chatm set filter", "",
                false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return null;
    }
}
