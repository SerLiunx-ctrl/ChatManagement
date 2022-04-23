package me.serliunx.chatmanagement.controllers.types;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.controllers.Controller;
import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.database.entities.User;
import me.serliunx.chatmanagement.enums.YamlFile;
import me.serliunx.chatmanagement.placeholders.Placeholders;
import me.serliunx.chatmanagement.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.List;

public final class BossBar implements Controller {

    private int maxTicks;
    private String barColor,barStyle,prefix,suffix,message;

    @Override
    public void show(String text, User user, Format format) {

        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        maxTicks = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getInt("bossBar.timer",80);
        barColor = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("bossBar.color", "GREEN");
        barStyle = ChatManagement.getInstance().getConfigManager().getByConfigName(YamlFile.YAML_MAIN.getValue())
                .getConfiguration().getString("bossBar.style", "SOLID");
        prefix = Placeholders.replacePlaceHolders(StringUtils.Color(format.getPrefix()), user.getPlayer());
        suffix = Placeholders.replacePlaceHolders(StringUtils.Color(format.getSuffix()), user.getPlayer());
        message = StringUtils.ColorWithPlayer(user.getPlayer(),text);

        new BukkitRunnable(){
            int ticks = 0;
            boolean done = false;

            final org.bukkit.boss.BossBar bossBar = Bukkit.createBossBar(prefix + message + suffix,
                    BarColor.valueOf(barColor), BarStyle.valueOf(barStyle));

            @Override
            public void run(){
                if(done){
                    bossBar.setVisible(false);
                    return;
                }
                players.forEach(bossBar::addPlayer);
                bossBar.setProgress(1 - (double) ticks / maxTicks);
                bossBar.setVisible(true);
                ticks++;
                if(ticks >= maxTicks)
                    done = true;
            }
        }.runTaskTimer(ChatManagement.getInstance(),0,1);
    }

    @Override
    public void showPrivateMessage(String text, User user, User target) {

    }
}
