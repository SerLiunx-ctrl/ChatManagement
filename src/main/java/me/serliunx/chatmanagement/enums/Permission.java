package me.serliunx.chatmanagement.enums;

public enum Permission {
    OTHER_PLAYER_CHATCOLOR("chatmanagement.player.chatcolor"),
    COMMAND_ADMIN_RELOAD("chatmanagement.command.admin.reload"),
    COMMAND_ADMIN_CONVERTER("chatmanagement.command.admin.converter"),
    COMMAND_ADMIN_PLAYER("chatmanagement.command.admin.player");

    private final String value;

    Permission(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
