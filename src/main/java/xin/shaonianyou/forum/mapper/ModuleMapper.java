package xin.shaonianyou.forum.mapper;

import xin.shaonianyou.forum.entity.Module;

import java.util.List;

public interface ModuleMapper {

    public List<Module> selectAll();

    public long moduleCount();

    public long addModule(Module module);

    public long delModule(long moduleid);

    public long updateModule(Module module);

    public Module selectModuleById(long moduleid);
}
