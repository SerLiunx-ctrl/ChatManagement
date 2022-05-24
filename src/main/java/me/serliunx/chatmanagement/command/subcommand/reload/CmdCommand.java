package me.serliunx.chatmanagement.command.subcommand.reload;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.util.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class CmdCommand extends Command {
    public CmdCommand() {
        super(Collections.singletonList("command"), "reload command file", "/chatm reload command", "",
                false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_COMMAND.getValue()).reloadConfig()){
            ChatManagement.getInstance().getCommandManager().reloadCommands();
            sender.sendMessage(StringUtils.Color("command config reloaded!"));
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
