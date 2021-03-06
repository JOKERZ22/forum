package xin.shaonianyou.forum.mapper;

import xin.shaonianyou.forum.entity.Category;

import java.util.List;

public interface CategoryMapper {

    public List<Category> selectAll();

    public  long categoryCount();

    public long addCategory(Category category);

    public long delCategory(long categoryid);

    public  long updateCategory(Category category);

    public Category selectCategoryById(long categoryid);
}
