package me.serliunx.chatmanagement.command.subcommand.reload;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.util.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class MainCommand extends Command {

    public MainCommand() {
        super(Collections.singletonList("main"), "reload main config file","/chatm reload main", "", false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue()).reloadConfig()){
            sender.sendMessage(StringUtils.Color("main config reloaded!"));
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
