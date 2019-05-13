package xin.shaonianyou.forum.service;

import xin.shaonianyou.forum.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    public List<Category> selectAll();

    public  long categoryCount();

    public Map<String, String> addCategory(Category category);

    public Map<String, String> delCategory(long categoryid);

   public Map<String,String> updateCategory(Category category);

    public Category selectCategoryById(long categoryid);
}
