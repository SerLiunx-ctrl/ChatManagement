package me.serliunx.chatmanagement.command.subcommand.player;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.command.Command;
import me.serliunx.chatmanagement.database.entity.User;
import me.serliunx.chatmanagement.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.sql.SQLException;
import java.time.Duration;
import java.util.*;

public final class SetCommand extends Command {

    public SetCommand() {
        super(Collections.singletonList("set"), "a set command about player", "/chatm player set <player name> <param>",
                "", false, Duration.ZERO);
    }

    @Override
    public boolean execute(CommandSender sender, String[] arguments) {
        if(arguments.length != 5){
            sender.sendMessage(StringUtils.Color(this.getSyntax() + " &f- " + this.getDescription()));
            return false;
        }
        Player player = Bukkit.getPlayer(arguments[2]);
        User user;
        if(player == null) {
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_player_not_found")
                    .replace("{0}", arguments[2]));
            return false;
        }

        user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());
        if(user == null) return false;

        switch (arguments[3]){
            case "prefix":
                return setPrefix(sender, user, arguments[2], arguments[4]);
            case "suffix":
                return setSuffix(sender, user, arguments[2], arguments[4]);
            case "prefixHolo":
                return setPrefixHolo(sender, user, arguments[2], arguments[4]);
            case "suffixHolo":
                return setSuffixHolo(sender, user, arguments[2], arguments[4]);
            case "textHolo":
                return setTextHolo(sender, user, arguments[2], arguments[4]);
            default:
                sender.sendMessage(StringUtils.Color(this.getSyntax() + " &f- " + this.getDescription()));
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
        if(args.length == 3){
            List<String> names = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach(p -> names.add(p.getDisplayName()));
            return names;
        }

        if(args.length == 4){
            return Arrays.asList("prefix", "suffix", "prefixHolo", "suffixHolo", "textHolo");
        }

        return Collections.emptyList();
    }

    private boolean setPrefix(CommandSender sender, User user, String playerName, String prefix){
        user.setPrefix(prefix);
        sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_prefix_set")
                .replace("{0}",playerName).replace("{1}", StringUtils.Color(prefix)));
        return updateSQL(user, "PREFIX", prefix);
    }

    private boolean setSuffix(CommandSender sender, User user, String playerName, String suffix){
        user.setSuffix(suffix);
        sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_suffix_set")
                .replace("{0}",playerName).replace("{1}", StringUtils.Color(suffix)));
        return updateSQL(user, "SUFFIX", suffix);
    }

    private boolean setSuffixHolo(CommandSender sender, User user, String playerName, String suffixHolo){
        if(suffixHolo.equals("null")){
            user.setSuffixHolo(null);
        }else{
            String[] s = suffixHolo.split("\\\\n", -1);
            user.setSuffixHolo(Arrays.asList(s));
        }

        boolean result = updateSQL(user, "SUFFIX_HOLO", suffixHolo);

        if(user.getSuffixHolo() != null){
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_suffixHolo_set")
                    .replace("{0}",playerName));
            for (String holo:user.getSuffixHolo())
                sender.sendMessage(StringUtils.Color(holo));
        }else{
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_suffixHolo_clear")
                    .replace("{0}",playerName));
        }
        return result;
    }

    private boolean setPrefixHolo(CommandSender sender, User user, String playerName, String prefixHolo){
        if(prefixHolo.equals("null")){
            user.setPrefixHolo(null);
        }else{
            String[] s = prefixHolo.split("\\\\n", -1);
            user.setPrefixHolo(Arrays.asList(s));
        }

        boolean result = updateSQL(user, "PREFIX_HOLO", prefixHolo);

        if(user.getPrefixHolo() != null){
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_prefixHolo_set")
                    .replace("{0}",playerName));
            for (String holo:user.getPrefixHolo())
                sender.sendMessage(StringUtils.Color(holo));
        }else {
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_prefixHolo_clear")
                    .replace("{0}",playerName));
        }
        return result;
    }

    private boolean setTextHolo(CommandSender sender, User user, String playerName, String textHolo){
        if(textHolo.equals("null")){
            user.setTextHolo(null);
        }else {
            String[] s = textHolo.split("\\\\n", -1);
            user.setTextHolo(Arrays.asList(s));
        }

        boolean result = updateSQL(user,"TEXT_HOLO", textHolo);

        if(user.getTextHolo() != null){
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_textHolo_set")
                    .replace("{0}",playerName));
            for (String holo:user.getTextHolo())
                sender.sendMessage(StringUtils.Color(holo));
        }else{
            sender.sendMessage(ChatManagement.getInstance().getLanguage().getSingleLine("command_textHolo_clear")
                    .replace("{0}",playerName));
        }
        return result;
    }

    private boolean updateSQL(User user, String attribute, String context){
        try{
            ChatManagement.getInstance().getSqlManager().updatePlayer(user, attribute, context);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            ChatManagement.getInstance().getLogger().warning(ChatManagement.getInstance().getLanguage()
                    .getSingleLine("sql_error_maynotsave"));
            return false;
        }
    }
}
