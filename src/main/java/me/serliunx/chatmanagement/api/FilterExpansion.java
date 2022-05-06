package me.serliunx.chatmanagement.api;

public abstract class FilterExpansion implements FilterHook{
    protected String author;
    protected String version;

    public FilterExpansion(String author, String version) {
        this.author = author;
        this.version = version;
    }


}
