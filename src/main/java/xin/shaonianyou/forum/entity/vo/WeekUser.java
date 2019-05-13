package xin.shaonianyou.forum.entity.vo;


//回复周榜
public class WeekUser {

    private Long frequency;
    private Long id;                        //id
    private String name;                    //用户名
    private String avatar_location;         //头像地址

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

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

    public String getAvatar_location() {
        return avatar_location;
    }

    public void setAvatar_location(String avatar_location) {
        this.avatar_location = avatar_location;
    }

    @Override
    public String toString() {
        return "WeekUser{" +
                "frequency=" + frequency +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", avatar_location='" + avatar_location + '\'' +
                '}';
    }
}
