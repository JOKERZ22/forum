package xin.shaonianyou.forum.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

    private int id;
    private String body;
    private String post_id;
    private String user_id;
    private Date date_created;

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", post_id='" + post_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", date_created=" + date_created +
                '}';
    }
}
