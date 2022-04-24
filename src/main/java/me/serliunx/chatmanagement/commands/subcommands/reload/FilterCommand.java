package me.serliunx.chatmanagement.commands.subcommands.reload;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.commands.Command;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.utils.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class FilterCommand extends Command {

    public FilterCommand() {
        super(Collections.singletonList("filter"), "reload filter file", "/chatm reload filter", "", false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_FILTER.getValue()).reloadConfig()){
            ChatManagement.getInstance().getFilterManager().reloadFilter();
            sender.sendMessage(StringUtils.Color("filter config reloaded!"));
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
