package me.serliunx.chatmanagement.commands.subcommands;

import me.serliunx.chatmanagement.commands.Command;
import me.serliunx.chatmanagement.commands.subcommands.player.GetCommand;
import me.serliunx.chatmanagement.commands.subcommands.player.InfoCommand;
import me.serliunx.chatmanagement.commands.subcommands.player.SetCommand;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerCommand extends Command {

    public Command getCommand, infoCommand, setCommand;

    public PlayerCommand() {
        super(Collections.singletonList("player"), "commands about player", "/chatm player", "", false, Duration.ofSeconds(2));
        getCommand = new GetCommand();
        infoCommand = new InfoCommand();
        setCommand = new SetCommand();
        addChilds(getCommand, infoCommand, setCommand);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> subs = new ArrayList<>();
        getChilds().forEach(sub -> subs.add(sub.getAliases().get(0)));
        return subs;
    }
}
