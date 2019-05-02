package xin.shaonianyou.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.shaonianyou.forum.entity.Category;
import xin.shaonianyou.forum.mapper.CategoryMapper;

import java.util.List;

@Service
public class CategoryService implements xin.shaonianyou.forum.service.CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }
}
