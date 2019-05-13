package xin.shaonianyou.forum.entity.vo;

import java.io.Serializable;
import java.util.Date;

//帖子加回复，用于帖子详情页
public class PostComment  implements Serializable {

    private Long postid;
    private String posttitle;
    private String commentbody;
    private Date commentdate;

    private static final long serialVersionUID = 1L;

    public Long getPostid() {
        return postid;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }

    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public String getCommentbody() {
        return commentbody;
    }

    public void setCommentbody(String commentbody) {
        this.commentbody = commentbody;
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "postid=" + postid +
                ", posttitle='" + posttitle + '\'' +
                ", commentbody='" + commentbody + '\'' +
                ", commentdate=" + commentdate +
                '}';
    }
}
