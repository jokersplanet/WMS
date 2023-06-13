package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import team.sxcoding.Entity.Department;

import java.util.List;

@Component
public interface DepartmentMapper  extends BaseMapper<Department> {
}
