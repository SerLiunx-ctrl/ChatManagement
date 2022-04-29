package me.serliunx.chatmanagement.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.serliunx.chatmanagement.ChatManagement;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class ClipPlaceholderAPI extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "chatmanagement";
    }

    @Override
    public @NotNull String getAuthor() {
        return "SerLiunx";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player,@NotNull String params) {
        return ChatManagement.getInstance().getPlaceholders().getPlaceHolder(player, params);
    }

}
