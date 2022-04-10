package me.serliunx.liunxrpg.enums;

public enum Permission {
    COMMAND_ADMIN_RELOAD("liunxrpg.command.admin.reload"),
    COMMAND_ADMIN_PLAYER("liunxrpg.command.admin.player");

    private final String value;

    Permission(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
