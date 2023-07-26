package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Status;
import team.sxcoding.Mapper.StatusMapper;
import team.sxcoding.Service.StatusService;

import java.util.List;


@Service("StatusService")
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements StatusService {


    @Override
    public List<Status> selectStatus() {
        return list(new QueryWrapper<Status>().orderByAsc("sequence"));
    }

    @Override
    public boolean isExistStatus(String status) {
        if(count(new QueryWrapper<Status>().eq("name",status))>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistStatus(Integer uid) {
        if(count(new QueryWrapper<Status>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean saveOrUpdateStatus(Status status) {
        return saveOrUpdate(status);
    }

    @Override
    public boolean deleteStatusById(Integer uid) {
        return removeById(uid);
    }
}
