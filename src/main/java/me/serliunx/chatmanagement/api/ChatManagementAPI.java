package me.serliunx.chatmanagement.api;

import me.serliunx.chatmanagement.ChatManagement;
import me.serliunx.chatmanagement.database.entity.Filter;
import java.util.List;

public class ChatManagementAPI {
    private ChatManagementAPI(){}

    public static List<Filter> getAllFilters(){
        return ChatManagement.getInstance().getFilterManager().getFilters();
    }
}
