package me.serliunx.chatmanagement.command.subcommand.player;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.database.entity.User;
import me.serliunx.chatmanagement.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class InfoCommand extends Command {

    public InfoCommand() {
        super(Collections.singletonList("info"), "show information about player.", "/chatm player info <player name>",
                "", false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(arguments.length != 3){
            sender.sendMessage(StringUtils.Color(this.getSyntax() + " &f- " + this.getDescription()));
            return false;
        }

        Player player = Bukkit.getPlayer(arguments[2]);
        if(player != null){
            User user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());
            if(user != null){
                sender.sendMessage(StringUtils.Color("&a&l " + player.getDisplayName() + "'s &a&lInformation"));
                sender.sendMessage(StringUtils.Color("&ePrefix: &r" + user.getPrefix()));
                sender.sendMessage(StringUtils.Color("&eSuffix: &r" + user.getSuffix()));
                return true;
            }
        }
        sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_player_not_found")
                .replace("{0}", arguments[2]));

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> names = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(p -> names.add(p.getDisplayName()));
        return names;
    }
}
