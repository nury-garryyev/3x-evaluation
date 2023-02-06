package io.mend.sast.model;

public class User {

    private String name;
    private int role;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, int role){
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole(){
        return role;
    }

    public void setRole(int role){
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + "\', " +
                "role=" + role + " " +
                '}';
    }
}
