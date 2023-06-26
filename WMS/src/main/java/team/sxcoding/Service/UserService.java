package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /*判断姓名是否存在*/
    boolean isExistUsername(String username);

    /*判断uid是否存在*/
    boolean isExistUid(Integer uid);

    /*判断部门是否存在*/
    boolean isExistDepartment(Integer uid);

    /*根据uid查询用户信息*/
    User selectUserByUid(Integer uid);

    /*根据员工编号查询用户信息*/
    User selectUserByNumber(String number);

    /*根据电话号码查询用户信息*/
    User selectUserByTelephone(String telephone);

    /*分页显示所有用户*/
    IPage<User> listUsers(Integer page, Integer count);

    /*根据uid查询用户信息（模糊查询）*/
    IPage<User> getUsersByUid(Integer uid, Integer page, Integer count);

    /*根据员工编号查询用户信息（模糊查询）*/
    IPage<User> getUsersByNumber(String number,Integer page,Integer count);

    /*根据电话号查询用户信息（模糊查询）*/
    IPage<User> getUsersByTelephone(String telephone,Integer page,Integer count);

    /*根据姓名查询用户信息（模糊查询）*/
    IPage<User> getUsersByUsername(String username,Integer page,Integer count);

    /*根据部门查询用户信息（模糊查询）*/
    IPage<User> getUsersByDepartment(Integer id,Integer page,Integer count);

    /*修改用户信息*/
    boolean updateUser(User user);

    /*删除用户信息*/
    boolean deleteUserByUid(Integer uid);



}

