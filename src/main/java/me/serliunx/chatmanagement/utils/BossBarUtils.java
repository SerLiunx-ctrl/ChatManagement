package me.serliunx.chatmanagement.utils;

import me.serliunx.chatmanagement.ChatManagement;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarUtils {
    private BossBarUtils(){}

    /**
     * 使用BossBar显示文字
     *
     * @param player 显示对象{@link Player}
     * @param text 需要显示的文字
     * @param barColor 颜色
     * @param barStyle 风格
     * @param period 刷新间隔
     * @param maxTicks 总共显示的时间
     */
    public static void showText(Player player, String text, BarColor barColor, BarStyle barStyle, int period, int maxTicks){
        new BukkitRunnable(){
            int ticks = 0;
            boolean done = false;

            final BossBar bar = Bukkit.createBossBar(text, barColor, barStyle);

            @Override
            public void run(){
                if(done){
                    bar.setVisible(false);
                    return;
                }
                bar.addPlayer(player);
                bar.setProgress(1 - (double) ticks / maxTicks);
                bar.setVisible(true);
                ticks++;
                if(ticks >= maxTicks)
                    done = true;

            }
        }.runTaskTimer(ChatManagement.getInstance(),0, period);
    }

    public static void showText(){

    }
}