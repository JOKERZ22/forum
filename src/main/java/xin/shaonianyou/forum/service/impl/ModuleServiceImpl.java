package xin.shaonianyou.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.shaonianyou.forum.entity.Module;
import xin.shaonianyou.forum.mapper.ModuleMapper;
import xin.shaonianyou.forum.service.ModuleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> selectAll() {
        return moduleMapper.selectAll();
    }

    @Override
    public long moduleCount() {
        return moduleMapper.moduleCount();
    }

    @Override
    public Map<String, String> addModule(Module module) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        long l = moduleMapper.addModule(module);
        if (l < 1) {
            resultMap.put("message", "插入失败");
        } else {
            resultMap.put("message", "1");
        }

        return resultMap;
    }

    @Override
    public Map<String, String> delModule(long moduleid) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        long l = moduleMapper.delModule(moduleid);
        if (l < 1) {
            resultMap.put("message", "删除失败");
        } else {
            resultMap.put("message", "1");
        }
        return resultMap;
    }

    @Override
    public Map<String, String> updateModule(Module module) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        long l = moduleMapper.updateModule(module);
        if (l < 1) {
            resultMap.put("message", "修改失败");
        } else {
            resultMap.put("message", "1");
        }
        return resultMap;
    }

    @Override
    public Module selectModuleById(long moduleid) {
        return moduleMapper.selectModuleById(moduleid);
    }
}
