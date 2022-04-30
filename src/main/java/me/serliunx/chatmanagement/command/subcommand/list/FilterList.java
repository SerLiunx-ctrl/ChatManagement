package me.serliunx.chatmanagement.command.subcommand.list;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.database.entity.Filter;
import me.serliunx.chatmanagement.util.StringUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FilterList extends Command {

    public FilterList() {
        super(Collections.singletonList("filter"), "list all filters", "/chatm list filter", "", false, Duration.ofSeconds(2));
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        List<Filter> filterList = ChatManagement.getInstance().getFilterManager().getFilters();
        List<TextComponent> textComponents = new ArrayList<>();

        for(Filter f: filterList){
            List<String> holo = new ArrayList<>((Arrays.asList("&fskip-permission: &e" + f.getPermission(),
                    "&fenable: &e" + f.isEnable(), "&freplace-by: &e" + f.getReplacement(), "&fnumberOfValues: &e"+
                            f.getValues().size()+"&b"
            )));
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
