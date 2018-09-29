package cn.uestc.note.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class Note implements Serializable{

    private String id;
    private String notebookId;
    private String userId;
    private String status_id;
    private String type_id;
    private String title;
    private String body;
    private long createtime;
    private long last_modify;

    public Note() {
    }
    public Note(String id, String notebookId, String userId, String status_id, String type_id,
                String title, String body, long createtime, long last_modify) {
        this.id = id;
        this.notebookId = notebookId;
        this.userId = userId;
        this.status_id = status_id;
        this.type_id = type_id;
        this.title = title;
        this.body = body;
        this.createtime = createtime;
        this.last_modify = last_modify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        return id != null ? id.equals(note.id) : note.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", notebookId='" + notebookId + '\'' +
                ", userId='" + userId + '\'' +
                ", status_id='" + status_id + '\'' +
                ", type_id='" + type_id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createtime=" + createtime +
                ", last_modify=" + last_modify +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(String notebookId) {
        this.notebookId = notebookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(long last_modify) {
        this.last_modify = last_modify;
    }
}
