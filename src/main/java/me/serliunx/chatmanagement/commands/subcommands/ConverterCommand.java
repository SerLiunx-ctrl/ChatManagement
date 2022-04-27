package me.serliunx.chatmanagement.commands.subcommands;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.commands.Command;
import me.serliunx.chatmanagement.enums.DriverType;
import me.serliunx.chatmanagement.enums.Permission;
import me.serliunx.chatmanagement.utils.StringUtils;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConverterCommand extends Command {

    public ConverterCommand() {
        super(Collections.singletonList("converter"), "data converter.", "/chatm converter", Permission.COMMAND_ADMIN_CONVERTER.getValue(), false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(arguments.length != 2){
            sender.sendMessage(StringUtils.Color(getSyntax() + " &f- " + getDescription()));
            return false;
        }
        if(!arguments[1].equals(DriverType.MYSQL.toString()) && !arguments[1].equals(DriverType.SQLITE.toString())){
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("sql_converter_notfound"));
            return false;
        }
        boolean result = ChatManagement.getInstance().getConverter().convert(DriverType.valueOf(arguments[1]), sender);
        if(!result){
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("sql_converter_failed"));
        }
        return result;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Arrays.asList(DriverType.SQLITE.toString(), DriverType.MYSQL.toString());
    }

}
