package xin.shaonianyou.forum.entity.vo;

import java.io.Serializable;
import java.util.Date;

//回复内容加用户信息，用于帖子详情页
public class CommentUser implements Serializable {

    private long user_id;
    private String name;
    private String avatar_location;
    private long post_id;
    private Date date_created;
    private long hit_count;
    private String body;
    private long comment_id;

    private static final long serialVersionUID = 1L;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_location() {
        return avatar_location;
    }

    public void setAvatar_location(String avatar_location) {
        this.avatar_location = avatar_location;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    @Override
    public String toString() {
        return "CommentUser{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", avatar_location='" + avatar_location + '\'' +
                ", post_id=" + post_id +
                ", date_created=" + date_created +
                ", hit_count=" + hit_count +
                ", body='" + body + '\'' +
                ", comment_id=" + comment_id +
                '}';
    }
}
