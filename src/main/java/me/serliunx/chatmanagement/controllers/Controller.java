package me.serliunx.chatmanagement.controllers;

import me.serliunx.chatmanagement.database.entities.Format;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public interface Controller {

    /**
     * <p>
     * 向指定玩家集合{@link Set} 展示文本信息.
     * <p>
     * @param text 文本信息
     * @param player 玩家: 发送方
     * @param format 格式 {@link Format}
     * @param recipients 接受方
     */
    void showMessage(@NotNull String text, Player player, Format format, Set<Player> recipients);
}
