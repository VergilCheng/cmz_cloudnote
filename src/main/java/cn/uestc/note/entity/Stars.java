package cn.uestc.note.entity;

import java.io.Serializable;

public class Stars implements Serializable{

    private String id;
    private String userId;
    private int star;

    public Stars(String id, String userId, int star) {
        this.id = id;
        this.userId = userId;
        this.star = star;
    }

    public Stars() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Stars{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", star=" + star +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stars stars = (Stars) o;

        return id != null ? id.equals(stars.id) : stars.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
