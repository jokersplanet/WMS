package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.sxcoding.Entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User>{

    /*登录功能*/
    @Select("SELECT password FROM user WHERE uid = #{uid}")
    String login(@Param("uid") Integer uid);

    /*注册功能*/
    @Insert("INSERT INTO user (number,password,username,gender,photo,privilege,birthday,department,telephone,address,duties) VALUES (#{number},#{password},#{username},#{gender},#{photo},#{privilege},#{birthday},#{department},#{telephone},#{address},#{duties})")
    Integer register(User user);

    /*判断员工编号是否重复*/
    @Select("SELECT COUNT(*) FROM user WHERE number = #{number}")
    Integer isExistNumber(@Param("number") String number);

    /*判断员工电话号码是否重复*/
    @Select("SELECT COUNT(*) FROM user WHERE telephone = #{telephone}")
    Integer isExistTelephone(@Param("telephone") String telephone);

    /*根据uid查询用户信息*/
    @Select("SELECT * FROM user WHERE uid = #{uid}")
    User selectUserByUid(@Param("uid") Integer uid);

    /*根据telephone查询用户信息*/
    @Select("SELECT * FROM user WHERE telephone = #{telephone}")
    User selectUserByTelephone(@Param("telephone") String telephone);

    /*根据number查询用户信息*/
    @Select("SELECT * FROM user WHERE number = #{number}")
    User selectUserByNumber(@Param("number") String number);

    /*判断uid是否存在*/
    @Select("SELECT COUNT(*) FROM user WHERE uid = #{uid}")
    Integer isExistUid(@Param("uid") Integer uid);

    /*分页显示所有用户信息*/
    @Select("SELECT uid,number,username,gender,department,telephone FROM user")
    IPage<User> listUsers(Page<User> page);
}
