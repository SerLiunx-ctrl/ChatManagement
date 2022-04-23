package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.enums.ChatType;
import me.serliunx.chatmanagement.enums.DefaultValue;
import me.serliunx.chatmanagement.enums.YamlFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FormatManager {

    private final Map<String, Format> formatMap;
    private FileConfiguration formatFile;

    public FormatManager(){
        formatMap = new HashMap<>();
        reloadFormats();
    }

    /**
     * 重载所有聊天格式
     */
    public void reloadFormats(){
        formatFile = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_FORMAT
                .getValue()).getConfiguration();
        loadFromFile();
    }

    /**
     * 匹配玩家的聊天格式, 如果玩家有设置自己格式的各项属性, 则会替换掉匹配到
     * 的格式的各项属性
     * @param player 用户
     * @return 匹配到的格式, 匹配到的格式区别于内存中已加载的格式组.如果没有匹配到, 则会返回默认的格式
     *  即在配置文件中所加载的名为 default 的格式组
     */
    public Format matchPlayerFormat(@NotNull Player player){
        List<Format> formats = new ArrayList<>();
        for (Format f: formatMap.values()){
            if(player.hasPermission(f.getPermission()) || f.getPermission() == null || f.getPermission().equals(""))
                formats.add(f);
        }
        if(formats.isEmpty())
            return formatMap.get("default");

        int max = 0;
        Format format = formatMap.get("default");
        User user = ChatManagement.getInstance().getUserManager().getUser(player.getUniqueId());

        for (Format f:formats){
            if(f.getPriority() > max){
                max = f.getPriority();
                format = (Format) f.clone();
                if(format == null)
                    format = formatMap.get("default");
            }
        }
        if(!user.getChatType().equals(format.getChatType()))
            format.setChatType(user.getChatType());

        if(!user.getPrefix().equals(DefaultValue.PREFIX.getValue()))
            format.setPrefix(user.getPrefix());

        if(!user.getSuffix().equals(DefaultValue.SUFFIX.getValue()))
            format.setSuffix(user.getSuffix());

        if(user.getPrefixHolo() != null)
            format.setPrefixHolo(user.getPrefixHolo());

        if(user.getSuffixHolo() != null)
            format.setSuffixHolo(user.getSuffixHolo());

        if(user.getTextHolo() != null)
            format.setTextHolo(user.getTextHolo());

        return format;
    }

    private void addFormat(Format format){
        formatMap.put(format.getName(), format);
    }

    private void loadFromFile(){
        formatMap.clear();
        for(String key:formatFile.getKeys(false)){
            //跳过重复的组
            if(formatMap.containsKey(key))
                continue;
            addFormat(new Format(key, formatFile.getString(key+".permission"), ChatType.valueOf(formatFile.getString(key+".type")),
                    formatFile.getString(key+".prefix"), formatFile.getString(key+".suffix"), formatFile.getInt(key+".priority"),
                    formatFile.getStringList(key+".holo_prefix"), formatFile.getStringList(key+".holo_suffix"),
                    formatFile.getStringList(key+".holo_text")));
        }
        ChatManagement.getInstance().getLogger().info("loaded " + formatMap.size() + " formats.");
    }
}
