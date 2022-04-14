package me.serliunx.chatmanagement.controllers;

import me.serliunx.chatmanagement.database.entities.Format;
import me.serliunx.chatmanagement.database.entities.User;

public interface Controller {

    /**
     * 发送聊天信息给在线玩家
     * @param user 聊天的发送方
     * @param format 格式
     */
    void show(User user, Format format);

    /**
     * 发送私密信息
     * @param user 发送方
     * @param target 接受方
     */
    void showPrivateMessage(User user, User target);
}
