package xin.shaonianyou.forum.entity;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private long id;             //主键
    private String title;       //帖子标题
    private String body;        //帖子内容
    private Date date_created;  //发帖日期
    private long user_id;       //发帖人
    private long module_id;     //模块ID
    private long category_id;    //分类ID
    private long comment_count;  //回复统计
    private long hit_count;      //点赞统计

    private static final long serialVersionUID = 1L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getModule_id() {
        return module_id;
    }

    public void setModule_id(long module_id) {
        this.module_id = module_id;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public long getComment_count() {
        return comment_count;
    }

    public void setComment_count(long comment_count) {
        this.comment_count = comment_count;
    }

    public long getHit_count() {
        return hit_count;
    }

    public void setHit_count(long hit_count) {
        this.hit_count = hit_count;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date_created=" + date_created +
                ", user_id=" + user_id +
                ", module_id=" + module_id +
                ", category_id=" + category_id +
                ", comment_count=" + comment_count +
                ", hit_count=" + hit_count +
                '}';
    }
}
