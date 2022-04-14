package me.serliunx.chatmanagement.commands.subcommands.reload;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.commands.Command;
import org.bukkit.command.CommandSender;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class AllCommand extends Command {

    public AllCommand() {
        super(Collections.singletonList("all"), "reload all config", "/lr reload all", "",
                false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        ChatManagement.getInstance().getConfigManager().reloadConfigs();
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
