package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.sxcoding.Entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {

    /*登录功能*/
    @Select("SELECT password FROM user WHERE uid = #{uid}")
    String login(@Param("uid") Integer uid);

    /*注册功能*/
    @Insert("INSERT INTO user (number,password,username,gender,photo,privilege,birthday,department,telephone,address,duties) VALUES (#{number},#{password},#{username},#{gender},#{photo},#{privilege},#{birthday},#{department},#{telephone},#{address},#{duties})")
    Integer register(User user);

    /*判断员工编号是否重复*/
    @Select("SELECT COUNT(*) FROM user WHERE number LIKE '%' || #{number} ||'%'")
    Integer isExistNumber(@Param("number") String number);

    /*判断员工电话号码是否重复*/
    @Select("SELECT COUNT(*) FROM user WHERE telephone LIKE '%' || #{telephone} ||'%'")
    Integer isExistTelephone(@Param("telephone") String telephone);

    /*根据uid查询用户信息*/
    @Select("SELECT * FROM user WHERE uid = #{uid}")
    User selectUserByUid(@Param("uid") Integer uid);

    /*根据telephone查询用户信息*/
    @Select("SELECT uid,number,username,gender,photo,birthday,department,telephone,address,duties FROM user WHERE telephone = #{telephone}")
    User selectUserByTelephone(@Param("telephone") String telephone);

    /*根据number查询用户信息*/
    @Select("SELECT uid,number,username,gender,photo,birthday,department,telephone,address,duties FROM user WHERE number = #{number}")
    User selectUserByNumber(@Param("number") String number);

    /*判断uid是否存在*/
    @Select("SELECT COUNT(*) FROM user WHERE uid LIKE '%' || #{uid} ||'%'")
    Integer isExistUid(@Param("uid") Integer uid);

    /*分页显示所有用户信息*/
    @Select("SELECT uid,number,username,gender,department,telephone FROM user")
    IPage<User> listUsers(Page<User> page);

    /*根据用户uid模糊查询用户信息*/
    @Select("SELECT uid,number,username,gender,department,telephone FROM user WHERE uid LIKE '%' || #{uid} || '%'")
    IPage<User> getUsersByUid(Page<User> page, @Param("uid") Integer uid);

    /*根据员工编号模糊查询用户信息*/
    @Select("SELECT uid,number,username,gender,department,telephone FROM user WHERE number LIKE '%' || #{number} || '%'")
    IPage<User> getUsersByNumber(Page<User> page, @Param("number") String number);

    /*根据用户电话号码模糊查询用户信息*/
    @Select("SELECT uid,number,username,gender,department,telephone FROM user WHERE telephone LIKE '%' || #{telephone} ||'%'")
    IPage<User> getUsersByTelephone(Page<User> page, @Param("telephone") String telephone);

    /*根据用户姓名模糊查询用户信息*/
    @Select("SELECT uid,number,username,gender,department,telephone FROM user WHERE username LIKE '%' || #{username} ||'%' ")
    IPage<User> getUsersByUsername(Page<User> page, @Param("username") String username);

    /*根据用户部门模糊查询用户信息*/
    @Select("SELECT uid,number,username,gender,department,telephone FROM user WHERE department LIKE '%' || #{department} ||'%' ")
    IPage<User> getUsersByDepartment(Page<User> page, @Param("department") Integer id);


    /*修改用户信息*/
    @Update("UPDATE user SET number = COALESCE(#{number},number), password = COALESCE(#{password},password), username = COALESCE(#{username}, username )," +
            "gender = COALESCE(#{gender},gender), photo = COALESCE(#{photo},photo), privilege = COALESCE(#{privilege},privilege), birthday = COALESCE(#{birthday},birthday)," +
            "telephone = COALESCE(#{telephone},telephone), address = COALESCE(#{address},address), duties = COALESCE(#{duties},duties) WHERE uid = #{uid}")
    Integer updateUser(User user);

    /*删除用户信息*/
    @Delete("DELETE FROM user WHERE uid = #{uid}")
    boolean deleteUserByUid(@Param("uid") Integer uid);

    /*判断姓名是否存在*/
    @Select("SELECT COUNT(*) FROM user WHERE username LIKE '%' || #{username} ||'%'")
    Integer isExistName(@Param("username") String username);



}

