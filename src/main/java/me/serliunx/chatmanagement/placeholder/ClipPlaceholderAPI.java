package me.serliunx.chatmanagement.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.serliunx.chatmanagement.ChatManagement;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class ClipPlaceholderAPI extends PlaceholderExpansion {
    private final ChatManagement plugin;

    public ClipPlaceholderAPI() {
        this.plugin = ChatManagement.getInstance();
    }

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
        return plugin.getPlaceholders().getPlaceHolder(player, params);
    }

}
