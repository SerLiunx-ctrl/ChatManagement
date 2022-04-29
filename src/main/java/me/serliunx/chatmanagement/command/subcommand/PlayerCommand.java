package me.serliunx.chatmanagement.command.subcommand;

import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.command.subcommand.player.InfoCommand;
import me.serliunx.chatmanagement.command.subcommand.player.SetCommand;
import me.serliunx.chatmanagement.enums.Permission;
import me.serliunx.chatmanagement.util.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerCommand extends Command {

    public Command infoCommand, setCommand;

    public PlayerCommand() {
        super(Collections.singletonList("player"), "commands about player", "/chatm player", Permission.COMMAND_ADMIN_PLAYER.getValue(), false, Duration.ofSeconds(2));
        infoCommand = new InfoCommand();
        setCommand = new SetCommand();
        addChilds(infoCommand, setCommand);
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
