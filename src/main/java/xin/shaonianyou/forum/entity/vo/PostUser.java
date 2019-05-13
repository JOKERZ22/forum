package xin.shaonianyou.forum.entity.vo;

import java.io.Serializable;
import java.util.Date;

//帖子加用户信息，用于首页，帖子首页
public class PostUser implements Serializable {
    private long user_id;
    private String user_name;
    private String avatar_location;
    private long post_id;
    private long category_id;
    private String category_name;
    private Date date_created;
    private long comment_count;
    private String title;

    private static final long serialVersionUID = 1L;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public long getComment_count() {
        return comment_count;
    }

    public void setComment_count(long comment_count) {
        this.comment_count = comment_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PostUser{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", avatar_location='" + avatar_location + '\'' +
                ", post_id=" + post_id +
                ", category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                ", date_created=" + date_created +
                ", comment_count=" + comment_count +
                ", title='" + title + '\'' +
                '}';
    }
}
