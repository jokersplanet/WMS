<<<<<<< HEAD
package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("number")
    private String number;

    @TableField("password")
    private String password;

    @TableField("username")
    private String username;

    @TableField("gender")
    private String gender;

    @TableField("photo")
    private String photo;

    @TableField("privilege")
    private String privilege;

    @TableField("birthday")
    private Date birthday;

    @TableField("department")
    private String department;

    @TableField("telephone")
    private String telephone;

    @TableField("address")
    private String address;

    @TableField("duties")
    private String duties;


}
=======
package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("number")
    private String number;

    @TableField("password")
    private String password;

    @TableField("username")
    private String username;

    @TableField("gender")
    private String gender;

    @TableField("photo")
    private String photo;

    @TableField("privilege")
    private String privilege;

    @TableField("birthday")
    private Date birthday;

    @TableField("department")
    private String department;

    @TableField("telephone")
    private String telephone;

    @TableField("address")
    private String address;

    @TableField("duties")
    private String duties;


}
>>>>>>> 0be10400409c0210cb86ebb1866173f09c6b461e
