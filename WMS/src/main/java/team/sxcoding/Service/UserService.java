package team.sxcoding.Service;

import team.sxcoding.Entity.User;

import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User>{

    /*用户登录*/
    boolean login(User user);

    /*用户注册*/
    User register(User user);

    /*判断员工编号是否重复*/
    boolean isExistNumber(String number);

    /*判断员工电话号码是否重复*/
    boolean isExistTelephone(String telephone);

    /*根据uid查询用户信息*/
    User selectUserByUid(Integer uid);


}
