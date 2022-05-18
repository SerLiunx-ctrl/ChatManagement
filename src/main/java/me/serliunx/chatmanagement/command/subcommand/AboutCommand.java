package me.serliunx.chatmanagement.command.subcommand;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class AboutCommand extends Command {
    private final ChatManagement instance;

    public AboutCommand() {
        super(Collections.singletonList("about"), "about command", "/chatm about", "",
                false, Duration.ofSeconds(2));
        instance = ChatManagement.getInstance();
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        for(String s:instance.getLanguage().getMultipleLine("about_plugin"))
            sender.sendMessage(s);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
