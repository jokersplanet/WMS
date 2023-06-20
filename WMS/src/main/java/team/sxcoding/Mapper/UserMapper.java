package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.sxcoding.Entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT user.*, department.name FROM user LEFT JOIN department ON user.department = department.uid WHERE user.uid = #{uid} ;")
    User getOneById(@Param("uid") Integer uid);

    @Select("SELECT user.*, department.name FROM user LEFT JOIN department ON user.department = department.uid WHERE user.number = #{number} ;")
    User getOneByNumber(@Param("number") String number);

    @Select("SELECT user.*, department.name FROM user LEFT JOIN department ON user.department = department.uid WHERE user.telephone = #{telephone} ;")
    User getOneByTelephone(@Param("telephone") String telephone);

    @Select("SELECT user.*, department.name FROM user LEFT JOIN department ON user.department = department.uid;")
    IPage<User> listUsers(Page<User> page);


    @Select("SELECT user.*, department.name FROM user LEFT JOIN department ON user.department = department.uid WHERE user.uid LIKE '%' || #{uid} || '%';")
    IPage<User> listUsersByUid(Page<User> page,@Param("uid")Integer uid);

    @Select("SELECT user.*, department.name FROM user LEFT JOIN department ON user.department = department.uid WHERE user.number LIKE '%' || #{number} || '%';")
    IPage<User> listUsersByNumber(Page<User> page,@Param("number")String number);

    @Select("SELECT user.*, department.name FROM user LEFT JOIN department ON user.department = department.uid WHERE user.telephone LIKE '%' || #{telephone} || '%';")
    IPage<User> listUsersByTelephone(Page<User> page,@Param("telephone")String telephone);

    @Select("SELECT user.*, department.name FROM user LEFT JOIN department ON user.department = department.uid WHERE user.username LIKE '%' || #{username} || '%';")
    IPage<User> listUsersByUsername(Page<User> page,@Param("username")String username);

    @Select("SELECT user.*, department.name FROM user LEFT JOIN department ON user.department = department.uid WHERE user.department LIKE '%' || #{department} || '%';")
    IPage<User> listUsersByDepartment(Page<User> page,@Param("department")Integer department);


}

