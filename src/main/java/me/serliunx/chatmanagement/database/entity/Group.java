package me.serliunx.chatmanagement.database.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 本插件中的群组类.
 */
public final class Group {

    private String name;
    private String description;
    private User owner;
    private Map<UUID, User> administrators;
    private Map<UUID, User> users;
    private int capacity;

    /**
     * 默认构造器.
     *
     * @param name  群组名称.
     * @param description  群组描述.
     * @param capacity 群组的最大人数上限.
     */
    public Group(String name, String description, int capacity) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        administrators = new HashMap<>();
        users = new HashMap<>();
    }

    /**
     * 保留空参数构造器
     */
    public Group(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Map<UUID, User> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(Map<UUID, User> administrators) {
        this.administrators = administrators;
    }

    public Map<UUID, User> getUsers() {
        return users;
    }

    public void setUsers(Map<UUID, User> users) {
        this.users = users;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getUsersNumber(){
        return users.size();
    }

    public boolean addUser(User user){
        if(users.containsKey(user.getUuid()))
            return false;
        users.put(user.getUuid(), user);
        return true;
    }

    public boolean addAdministrator(User admin){
        if(administrators.containsKey(admin.getUuid()))
            return false;
        administrators.put(admin.getUuid(), admin);
        return true;
    }
}
