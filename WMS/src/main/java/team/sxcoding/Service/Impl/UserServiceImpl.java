package team.sxcoding.Service.Impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.*;

import team.sxcoding.Entity.PageResult;
import team.sxcoding.Entity.User;
import team.sxcoding.Mapper.UserMapper;
import team.sxcoding.Service.UserService;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    /*用户登录功能*/
    @Override
    public boolean login(User user){
        if(baseMapper.login(user.getUid()).equals(user.getPassword())){
            return true;
        }
        return false;
    }

    /*用户注册*/
    @Override
    public User register(User user){
         baseMapper.register(user);
         return baseMapper.selectUserByTelephone(user.getTelephone());
    }

    /*判断员工编号是否重复*/
    @Override
    public boolean isExistNumber(String number){
        if(baseMapper.isExistNumber(number)>0){
            return true;
        }
        return false;
    }

    /*判断员工电话号码是否重复*/
    @Override
    public boolean isExistTelephone(String telephone){
        if(baseMapper.isExistTelephone(telephone)>0){
            return true;
        }
        return false;
    }

    /*根据uid查询用户信息*/
    @Override
    public User selectUserByUid(Integer uid){
        return baseMapper.selectUserByUid(uid);
    }

     /*判断uid是否存在*/
    @Override
    public boolean isExistUid(Integer uid){
        if(baseMapper.isExistUid(uid)>0){
            return true;
        }
        return false;
    }


    /*根据员工编号查询用户信息*/
    @Override
    public User selectUserByNumber(String number){
        return baseMapper.selectUserByNumber(number);
    }

    /*根据电话号码查询用户信息*/
    @Override
    public User selectUserByTelephone(String telephone){
        return baseMapper.selectUserByTelephone(telephone);
    }


    /*分页显示所有用户*/
    @Override
    public PageResult<User> listUsers(Integer page,Integer count){
        Page<User> pageUser = new Page<>(page, count);
        IPage<User> userList = baseMapper.listUsers(pageUser);

        PageResult<User> pageResult = new PageResult<>();
        pageResult.setPages(userList.getPages());
        pageResult.setData(userList.getRecords());
        return pageResult;
    }


}

