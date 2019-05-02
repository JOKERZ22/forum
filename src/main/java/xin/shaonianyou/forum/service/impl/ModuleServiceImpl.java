package xin.shaonianyou.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.shaonianyou.forum.entity.Module;
import xin.shaonianyou.forum.mapper.ModuleMapper;
import xin.shaonianyou.forum.service.ModuleService;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> selectAll() {
        return moduleMapper.selectAll();
    }
}
