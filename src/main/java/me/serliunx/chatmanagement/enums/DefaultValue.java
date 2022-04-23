package me.serliunx.chatmanagement.enums;

public enum DefaultValue {
    PREFIX("default"),
    SUFFIX("default");

    private final String value;

    DefaultValue(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
