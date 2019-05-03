package xin.shaonianyou.forum.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

    private long id;
    private String body;
    private long post_id;
    private long user_id;
    private Date date_created;
    private long hit_count;
    private long comment_id;

    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public long getHit_count() {
        return hit_count;
    }

    public void setHit_count(long hit_count) {
        this.hit_count = hit_count;
    }

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", post_id=" + post_id +
                ", user_id=" + user_id +
                ", date_created=" + date_created +
                ", hit_count=" + hit_count +
                ", comment_id=" + comment_id +
                '}';
    }
}
