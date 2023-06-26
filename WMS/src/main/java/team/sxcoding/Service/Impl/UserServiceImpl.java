package team.sxcoding.Service.Impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.*;

import team.sxcoding.Entity.User;
import team.sxcoding.Mapper.UserMapper;
import team.sxcoding.Service.UserService;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    /*用户登录功能*/
    @Override
    public boolean login(User user){
        if(baseMapper.selectOne(new QueryWrapper<User>().select("password").eq("uid",user.getUid())).equals(user.getPassword())){
            return true;
        }
        return false;
    }

    /*用户注册*/
    @Override
    public User register(User user){
         saveOrUpdate(user);
         return getOne(new QueryWrapper<User>().eq("telephone",user.getTelephone()));
    }

    /*判断员工编号是否重复*/
    @Override
    public boolean isExistNumber(String number){
        if(count(new QueryWrapper<User>().eq("number",number))>0){
            return true;
        }
        return false;
    }

    /*判断员工电话号码是否重复*/
    @Override
    public boolean isExistTelephone(String telephone){
        if(count(new QueryWrapper<User>().eq("telephone",telephone))>0){
            return true;
        }
        return false;
    }

    /*根据uid查询用户信息*/
    @Override
    public User selectUserByUid(Integer uid){
        return baseMapper.getOneById(uid);
    }

     /*判断uid是否存在*/
    @Override
    public boolean isExistUid(Integer uid){
        if(count(new QueryWrapper<User>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    /*判断部门下是否存在用户*/
    @Override
    public boolean isExistDepartment(Integer department){
        if(count(new QueryWrapper<User>().eq("department",department))>0){
            return true;
        }
        return false;
    }


    /*根据员工编号查询用户信息*/
    @Override
    public User selectUserByNumber(String number){
        return baseMapper.getOneByNumber(number);
    }

    /*根据电话号码查询用户信息*/
    @Override
    public User selectUserByTelephone(String telephone){
        return baseMapper.getOneByTelephone(telephone);
    }


    /*分页显示所有用户*/
    @Override
    public IPage<User> listUsers(Integer page,Integer count){
        return baseMapper.listUsers(new Page<>(page, count));
    }

    /*根据uid查询用户信息（模糊查询）*/
    @Override
    public IPage<User> getUsersByUid(Integer uid, Integer page, Integer count){
        return baseMapper.listUsersByUid(new Page<>(page,count),uid);
    }

    /*根据员工编号查询用户信息（模糊查询）*/
    @Override
    public IPage<User> getUsersByNumber(String number,Integer page,Integer count){
        return baseMapper.listUsersByNumber(new Page<>(page,count),number);
    }

    /*根据电话号查询用户信息（模糊查询）*/
    @Override
    public IPage<User> getUsersByTelephone(String telephone, Integer page, Integer count){
        return baseMapper.listUsersByTelephone(new Page<>(page,count),telephone);
    }

    /*根据姓名查询用户信息（模糊查询）*/
    @Override
    public IPage<User> getUsersByUsername(String username,Integer page,Integer count){
        return baseMapper.listUsersByUsername(new Page<>(page,count),username);
    }

    /*根据部门查询用户信息（模糊查询）*/
    @Override
    public  IPage<User> getUsersByDepartment(Integer department,Integer page,Integer count){
        return baseMapper.listUsersByDepartment(new Page<>(page,count),department);
    }


    /*修改用户信息*/
    @Override
    public boolean updateUser(User user){
        return updateById(user);
    }

    /*删除用户信息*/
    @Override
    public boolean deleteUserByUid(Integer uid){
        return removeById(uid);
    }

    /*判断姓名是否存在*/
    @Override
    public boolean isExistUsername(String username){
        if(count(new QueryWrapper<User>().like("username",username))>0){
            return true;
        }
        return false;
    }



}

