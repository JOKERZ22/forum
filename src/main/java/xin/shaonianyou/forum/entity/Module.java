package xin.shaonianyou.forum.entity;

import java.io.Serializable;
import java.util.Date;

public class Module implements Serializable {
    private int id;
    private String name;
    private String display_name;
    private String username;
    private Date date_created;

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", display_name='" + display_name + '\'' +
                ", username='" + username + '\'' +
                ", date_created=" + date_created +
                '}';
    }
}
