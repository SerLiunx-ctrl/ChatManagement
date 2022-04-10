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

public class LanguageCommand extends Command {

    public LanguageCommand(){
        super(Collections.singletonList("lang"), "reload language file","/lr reload lang",
                Permission.COMMAND_ADMIN_RELOAD.getValue(), false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(LiunxRPG.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_LANGUAGE.getValue()).reloadConfig()){
            sender.sendMessage(StringUtils.Color("language config reloaded!"));
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
