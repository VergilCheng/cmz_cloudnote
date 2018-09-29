package cn.uestc.note.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Notebook implements Serializable {

    private String id;
    private String userId;
    private String name;
    private String type;
    private String thedesc;
    private Timestamp createtime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notebook notebook = (Notebook) o;

        return id != null ? id.equals(notebook.id) : notebook.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", thedesc='" + thedesc + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThedesc() {
        return thedesc;
    }

    public void setThedesc(String thedesc) {
        this.thedesc = thedesc;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Notebook() {
    }

    public Notebook(String id, String userId, String name, String type, String thedesc, Timestamp createtime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.thedesc = thedesc;
        this.createtime = createtime;
    }
}
