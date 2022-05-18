package me.serliunx.chatmanagement.enums;

public enum Permission {
    OTHER_PLAYER_CHATCOLOR("chatmanagement.player.chatcolor"),
    COMMAND_ADMIN_RELOAD("chatmanagement.command.admin.reload"),
    COMMAND_ADMIN_CONVERTER("chatmanagement.command.admin.converter"),
    COMMAND_ADMIN_LIST("chatmanagement.command.admin.list"),
    COMMAND_ADMIN_PLAYER("chatmanagement.command.admin.player"),
    COMMAND_ADMIN_SET("chatmanagement.command.admin.set");

    private final String value;

    Permission(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
