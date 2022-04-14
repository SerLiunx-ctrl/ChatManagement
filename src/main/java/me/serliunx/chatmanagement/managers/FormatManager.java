package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.database.entities.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FormatManager {

    private final Map<String, Format> formatMap;

    public FormatManager(){
        formatMap = new HashMap<>();
        loadFromFile();
    }

    /**
     * 重载所有聊天格式
     */
    public void reloadFormats(){
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

        if(!user.getPrefix().equals("default"))
            format.setPrefix(user.getPrefix());

        if(!user.getSuffix().equals("default"))
            format.setSuffix(user.getSuffix());

        if(user.getPrefixHolo() != null)
            if(!user.getPrefixHolo().isEmpty())
                format.setPrefixHolo(user.getPrefixHolo());

        if(user.getSuffixHolo() != null)
            if(!user.getSuffixHolo().isEmpty())
                format.setSuffixHolo(user.getSuffixHolo());

        if(user.getTextHolo() != null)
            if(!user.getTextHolo().isEmpty())
                format.setTextHolo(user.getTextHolo());

        return format;
    }

    /**
     * 新增一个聊天格式, 不允许重复添加.
     * @param format 具体格式
     * @return 如果已存在同名格式将返回假, 否则返回真.
     */
    public boolean addFormat(Format format){
        if(formatMap.containsKey(format.getName()))
            return false;
        formatMap.put(format.getName(), format);
        return true;
    }

    private void loadFromFile(){

    }
}
