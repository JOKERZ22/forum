package xin.shaonianyou.forum.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long id;                    //id
    private String name;                //用户名
    private String password;            //密码
    private String email;               //邮箱
    private Integer gender;             //性别
    private Long point;                 //积分
    private Date regDate;               //注册日期
    private Date updateDate;            //修改日期
    private Integer status;             //状态，默认为0，
    private String proflie;            //个人简介
    private String avatar_location;     //头像地址
    private Integer is_administrator;   //是否为管理员，默认为0，1-是

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProflie() {
        return proflie;
    }

    public void setProflie(String proflie) {
        this.proflie = proflie;
    }

    public String getAvatar_location() {
        return avatar_location;
    }

    public void setAvatar_location(String avatar_location) {
        this.avatar_location = avatar_location;
    }

    public Integer getIs_administrator() {
        return is_administrator;
    }

    public void setIs_administrator(Integer is_administrator) {
        this.is_administrator = is_administrator;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", point=" + point +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", status=" + status +
                ", proflie='" + proflie + '\'' +
                ", avatar_location='" + avatar_location + '\'' +
                ", is_administrator=" + is_administrator +
                '}';
    }
}
