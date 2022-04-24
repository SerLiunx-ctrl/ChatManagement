package me.serliunx.chatmanagement.commands.subcommands.reload;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.commands.Command;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.utils.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class FormatCommand extends Command {

    public FormatCommand(){
        super(Collections.singletonList("format"), "reload format file", "/chatm reload format", "", false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_FORMAT.getValue()).reloadConfig()){
            ChatManagement.getInstance().getFormatManager().reloadFormats();
            sender.sendMessage(StringUtils.Color("format config reloaded!"));
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
