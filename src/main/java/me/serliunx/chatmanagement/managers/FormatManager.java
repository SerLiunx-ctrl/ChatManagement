package me.serliunx.chatmanagement.managers;

import me.serliunx.chatmanagement.database.entities.Format;
import java.util.HashMap;
import java.util.Map;

public final class FormatManager {

    private final Map<String, Format> formatMap;

    public FormatManager(){
        formatMap = new HashMap<>();
    }

    public void reloadFormats(){
        this.loadFromFile();
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
