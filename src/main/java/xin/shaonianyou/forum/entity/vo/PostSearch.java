package xin.shaonianyou.forum.entity.vo;

import java.io.Serializable;

public class PostSearch implements Serializable {

    private long moduleid;
    private long categoryid;
    private long  sortby;

    private static final long serialVersionUID = 1L;

    public long getModuleid() {
        return moduleid;
    }

    public void setModuleid(long moduleid) {
        this.moduleid = moduleid;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public long getSortby() {
        return sortby;
    }

    public void setSortby(long sortby) {
        this.sortby = sortby;
    }

    @Override
    public String toString() {
        return "PostSearch{" +
                "moduleid=" + moduleid +
                ", categoryid=" + categoryid +
                ", sortby=" + sortby +
                '}';
    }
}
