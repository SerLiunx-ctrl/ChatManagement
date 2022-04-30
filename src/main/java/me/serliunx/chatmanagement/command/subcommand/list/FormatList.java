package me.serliunx.chatmanagement.command.subcommand.list;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.database.entity.Format;
import me.serliunx.chatmanagement.util.StringUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FormatList extends Command {

    public FormatList() {
        super(Collections.singletonList("format"), "list all formats.", "/chatm list format", "", false, Duration.ofSeconds(2));
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        List<Format> formatList = new ArrayList<>(ChatManagement.getInstance().getFormatManager().getFormatMap().values());
        List<TextComponent> textComponents = new ArrayList<>();
        for(Format f: formatList){
            List<String> holo = new ArrayList<>(Arrays.asList("permission: "+f.getPermission(), "priority: "+f.getPriority(),
                    "type: "+f.getChatType(), "prefix: "+f.getPrefix(), "suffix: "+f.getSuffix(), "holo_prefix: " + f.getPrefixHolo(),
                    "holo_suffix: "+f.getSuffixHolo(), "holo_text: "+f.getTextHolo()));

            textComponents.add(StringUtils.newTextComponent(f.getName(),holo));
        }
        sender.sendMessage(StringUtils.Color("&a=================="));
        textComponents.forEach(t -> sender.spigot().sendMessage(t));
        sender.sendMessage(StringUtils.Color("&a=================="));

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
