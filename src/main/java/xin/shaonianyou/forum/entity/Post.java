package xin.shaonianyou.forum.entity;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private int id;             //主键
    private String title;       //帖子标题
    private String body;        //帖子内容
    private Date date_created;  //发帖日期
    private long user_id;       //发帖人
    private  int module_id;     //模块ID
    private int category_id;    //分类ID
    private int comment_count;  //回复统计
    private int hit_count;      //点赞统计

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getHit_count() {
        return hit_count;
    }

    public void setHit_count(int hit_count) {
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
