<<<<<<< HEAD
package team.sxcoding.Service;

import team.sxcoding.Entity.PageResult;
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

    /*判断uid是否存在*/
    boolean isExistUid(Integer uid);

    /*根据uid查询用户信息*/
    User selectUserByUid(Integer uid);

    /*根据员工编号查询用户信息*/
    User selectUserByNumber(String number);

    /*根据电话号码查询用户信息*/
    User selectUserByTelephone(String telephone);

    /*分页显示所有用户*/
    PageResult<User> listUsers(Integer page,Integer count);


}
=======
package team.sxcoding.Service;

import team.sxcoding.Entity.PageResult;
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

    /*判断uid是否存在*/
    boolean isExistUid(Integer uid);

    /*根据uid查询用户信息*/
    User selectUserByUid(Integer uid);

    /*根据员工编号查询用户信息*/
    User selectUserByNumber(String number);

    /*根据电话号码查询用户信息*/
    User selectUserByTelephone(String telephone);

    /*分页显示所有用户*/
    PageResult<User> listUsers(Integer page,Integer count);


}
>>>>>>> 0be10400409c0210cb86ebb1866173f09c6b461e
