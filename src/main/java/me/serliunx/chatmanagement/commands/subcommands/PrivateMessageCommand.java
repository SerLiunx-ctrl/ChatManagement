package me.serliunx.chatmanagement.commands.subcommands;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.commands.Command;
import me.serliunx.chatmanagement.database.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrivateMessageCommand extends Command {

    public PrivateMessageCommand() {
        super(Collections.singletonList("pm"), "private message command", "/chatm pm", "", true, Duration.ofSeconds(2));
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        Player player = (Player) sender;
        User user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());
        if(arguments.length == 1){
            switchPmstatus(player, user);
            return true;
        }
        if(!user.isPmStatus()){
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("privatemessage_selfoff"));
            return false;
        }
        Player target = Bukkit.getPlayer(arguments[1]);
        if(target != null && target.isOnline()){
            if(target.getName().equals(player.getName())){
                sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("privatemessage_cannnotpmyou"));
                return false;
            }
            User targetUser = ChatManagement.getInstance().getUserManager().getUser(target.getUniqueId());
            if(targetUser.isPmStatus()){
                user.setAnotherUUID(player.getUniqueId());
                return true;
            }
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("privatemessage_off"));
            return false;
        }
        sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_player_not_found")
                .replace("{0}", arguments[1]));
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> names = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(p -> names.add(p.getDisplayName()));
        names.remove(commandSender.getName());
        return names;
    }

    private void switchPmstatus(Player sender, User user){
        if(user.isPmStatus()){
            user.setPmStatus(false);
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("privatemessage_switch")
                    .replace("{0}",ChatManagement.getInstance().getLanguage().getSingleLine("placeholder_disable")));
        }else{
            user.setPmStatus(true);
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("privatemessage_switch")
                    .replace("{0}",ChatManagement.getInstance().getLanguage().getSingleLine("placeholder_enable")));
        }
        try{
            ChatManagement.getInstance().getSqlManager().updatePlayer(user, "PRIVATE_MESSAGE",
                    String.valueOf(user.isPmStatus()));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
