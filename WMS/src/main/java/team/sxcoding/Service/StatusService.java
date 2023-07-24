package team.sxcoding.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Status;

import java.util.List;

public interface StatusService extends IService<Status> {


    List<Status> selectStatus();

    boolean isExistStatus(String status);


    boolean isExistStatus(Integer uid);

    boolean saveOrUpdateStatus(Status status);

    boolean deleteStatusById(Integer uid);
}
