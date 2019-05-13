package xin.shaonianyou.forum.service;

import xin.shaonianyou.forum.entity.Module;

import java.util.List;
import java.util.Map;

public interface ModuleService {

    public List<Module> selectAll();

    public long moduleCount();

    public Map<String, String> addModule(Module module);

    public Map<String, String> delModule(long moduleid);

    public Map<String, String> updateModule(Module module);

    public Module selectModuleById(long moduleid);
}
