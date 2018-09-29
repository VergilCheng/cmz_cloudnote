package cn.uestc.note.entity;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 123412341234L;

    private String id;
    private String name;
    private String password;
    private String token;
    private String nick;

    public User() {

    }

    public User(String id, String name, String password, String token, String nick) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.token = token;
        this.nick = nick;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getNick() {
        return nick;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }

    //有id一定要重写hashcode和equals方法，因为id是数据库中的主键
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
