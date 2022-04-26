package me.serliunx.chatmanagement.commands.subcommands.player;

import me.serliunx.chatmanagement.commands.Command;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class SetCommand extends Command {

    public SetCommand() {
        super(Collections.singletonList("set"), "a set command about player", "/chatm player set <param>",
                "", false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
