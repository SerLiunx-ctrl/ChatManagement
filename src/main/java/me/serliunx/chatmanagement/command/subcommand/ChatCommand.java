package me.serliunx.chatmanagement.command.subcommand;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.database.entity.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class ChatCommand extends Command {

    public ChatCommand() {
        super(Collections.singletonList("chat"), "switch your chat status", "/chatm chat", "", true, Duration.ofSeconds(2));
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        Player player = (Player) sender;
        User user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());

        user.setChatStatus(!user.getChatStatus());
        sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("chatstatus_switch")
                .replace("{0}",user.getChatStatus() ? ChatManagement.getInstance().getLanguage()
                        .getSingleLine("placeholder_enable") : ChatManagement.getInstance().getLanguage()
                        .getSingleLine("placeholder_disable")));

        try{
            ChatManagement.getInstance().getSqlManager().updatePlayer(user, "CHAT_STATUS",
                    String.valueOf(user.getChatStatus()));
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
