package me.serliunx.chatmanagement.util;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.enums.YamlFile;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本插件的语言管理类<p>
 * 用户保存和快速获取已加载的语言文件
 */
public class Language {
    private final Map<String, String> singleLine = new HashMap<>();
    private final Map<String, List<String>> multipleLine = new HashMap<>();
    private final FileConfiguration langFile;

    public Language() {
        langFile = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_LANGUAGE.getValue()).getConfiguration();
        this.loadLanguage();
    }

    private void loadLanguage() {
        for (String key : langFile.getKeys(false)) {
            if (langFile.isList(key)) {
                List<String> list = new ArrayList<>();

                for (String s : langFile.getStringList(key)) {
                    list.add(StringUtils.Color(s));
                }
                multipleLine.put(key, list);
                continue;
            }
            singleLine.put(key, StringUtils.Color(langFile.getString(key, "error")));
        }
    }

    public String getSingleLine(String loc) {
        return singleLine.get(loc);
    }

    public List<String> getMultipleLine(String loc) {
        return multipleLine.get(loc);
    }

}