package xin.shaonianyou.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.shaonianyou.forum.entity.Category;
import xin.shaonianyou.forum.mapper.CategoryMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements xin.shaonianyou.forum.service.CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public long categoryCount() {
        return categoryMapper.categoryCount();
    }

    @Override
    public Map<String, String> addCategory(Category category) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        long l = categoryMapper.addCategory(category);
        if (l < 1) {
            resultMap.put("message", "插入失败");
        } else {
            resultMap.put("message", "1");
        }
        return resultMap;
    }

    @Override
    public Map<String, String> delCategory(long categoryid) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        long l = categoryMapper.delCategory(categoryid);
        if (l < 1) {
            resultMap.put("message", "删除失败");
        } else {
            resultMap.put("message", "1");

        }
        return resultMap;
    }

    @Override
    public Map<String, String> updateCategory(Category category) {
        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        long l = categoryMapper.updateCategory(category);
        if (l < 1) {
            resultMap.put("message", "修改失败");
        } else {
            resultMap.put("message", "1");
        }
        return resultMap;
    }

    @Override
    public Category selectCategoryById(long categoryid) {
        return categoryMapper.selectCategoryById(categoryid);
    }

}
