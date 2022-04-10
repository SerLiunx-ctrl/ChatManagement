package me.serliunx.liunxrpg.commands.subcommands;

import me.serliunx.liunxrpg.LiunxRPG;
import me.serliunx.liunxrpg.commands.Command;
import me.serliunx.liunxrpg.utils.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(Collections.singletonList("help"), "show help pages.", "/lr help", "", false, Duration.ofSeconds(2));
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(arguments.length != 2){
            sender.sendMessage(StringUtils.Color("&a&l========== &e&lLiunxRPG Command &a&l=========="));
            for(Command c: LiunxRPG.getInstance().getCommandManager().getCommands()){
                sender.sendMessage(StringUtils.Color(c.getSyntax() + " &f- " + c.getDescription()));
            }
            sender.sendMessage(StringUtils.Color("&a&l===================================="));

            return true;
        }

        for(Command c:LiunxRPG.getInstance().getCommandManager().getCommands()){
            if(arguments[1].equals(c.getAliases().get(0))){
                if(sender.hasPermission(c.getPermission()) || c.getPermission().equals("")){
                    sender.sendMessage(StringUtils.Color(c.getSyntax() + " &f- " + c.getDescription()));
                }

                if(!c.getAliases().isEmpty()){
                    sender.sendMessage(StringUtils.Color("&a可选参数:"));
                    for(Command sub:c.getChilds()){
                        sender.sendMessage(StringUtils.Color("  " + sub.getAliases().get(0) + " &e- &b" + sub.getDescription()));
                    }
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        if(args.length == 2){
            List<String> subs = new ArrayList<>();
            for(Command c:LiunxRPG.getInstance().getCommandManager().getCommands()){
                if(c instanceof HelpCommand)
                    continue;

                if(commandSender.hasPermission(c.getPermission()) || c.getPermission().equals("")){
                    subs.add(c.getAliases().get(0));
                }
            }
            return subs;
        }

        return Collections.emptyList();
    }
}
