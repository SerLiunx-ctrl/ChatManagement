package me.serliunx.liunxrpg.commands.subcommands.reload;

import me.serliunx.liunxrpg.LiunxRPG;
import me.serliunx.liunxrpg.commands.Command;
import me.serliunx.liunxrpg.enums.Permission;
import me.serliunx.liunxrpg.enums.YamlFile;
import me.serliunx.liunxrpg.utils.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class MainCommand extends Command {

    public MainCommand() {
        super(Collections.singletonList("main"), "reload main config file","/lr reload main",
                Permission.COMMAND_ADMIN_RELOAD.getValue(), false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(LiunxRPG.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue()).reloadConfig()){
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
