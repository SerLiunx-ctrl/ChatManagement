package me.serliunx.chatmanagement.enums;

public enum YamlFile {

    YAML_MAIN("config"),
    YAML_COMMAND("command"),
    YAML_LANGUAGE("lang"),
    YAML_FORMAT("format"),
    YAML_FILTER("filter");

    private final String value;

    YamlFile(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
