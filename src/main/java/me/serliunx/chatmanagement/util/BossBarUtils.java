package me.serliunx.chatmanagement.util;

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
        showText(player,text,barColor,barStyle,period,maxTicks,0);
    }

    /**
     * 使用BossBar显示文字
     *
     * @param player 显示对象
     * @param text 显示文本
     * @param period 执行间隔
     * @param maxTicks 最大执行时间, tick.
     */
    public static void showText(Player player, String text, int period, int maxTicks){
        showText(player,text,BarColor.GREEN,BarStyle.SOLID,period,maxTicks);
    }

    /**
     * 使用BossBar显示文字
     *
     * @param player 显示对象{@link Player}
     * @param text 需要显示的文字
     * @param barColor 颜色
     * @param barStyle 风格
     * @param period 刷新间隔
     * @param maxTicks 总共显示的时间
     * @param delay 执行延时
     */
    public static void showText(Player player, String text, BarColor barColor, BarStyle barStyle, int period,
                                int maxTicks, int delay){
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
        }.runTaskTimer(ChatManagement.getInstance(),delay, period);

    }
}
