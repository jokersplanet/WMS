<<<<<<< HEAD
package team.sxcoding.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Entity.User;
import team.sxcoding.Service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /*uid查询用户信息*/
    @GetMapping("getUserDetailByUid")
    public ServerResponse getUserDetailByUid(Integer uid){
        if (uid == null) {
            return ServerResponse.ErrorMessage("uid为空");
        }
        if(userService.isExistUid(uid)){
            User user = userService.selectUserByUid(uid);
            return ServerResponse.Success("查询成功",user);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    /*员工编号查询用户信息*/
    @GetMapping("getUserDetailByNumber")
    public ServerResponse getUserDetailByNumber(String number){
        if (number.equals(null)) {
            return ServerResponse.ErrorMessage("员工编号为空");
        }
        if(userService.isExistNumber(number)){
            User user = userService.selectUserByNumber(number);
            return ServerResponse.Success("查询成功",user);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    /*手机号查询用户信息*/
    @GetMapping("getUserDetailByTelephone")
    public ServerResponse getUserDetailByTelephone(String telephone){
        if (telephone.equals(null)) {
            return ServerResponse.ErrorMessage("员工编号为空");
        }
        if(userService.isExistTelephone(telephone)){
            User user = userService.selectUserByTelephone(telephone);
            return ServerResponse.Success("查询成功",user);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    /*查看所有用户信息*/
    @GetMapping("listUsers")
    public ServerResponse listUsers(Integer page,Integer count){
        if ( page == null || count == null) {
            return ServerResponse.Error();
        }else {
            PageResult<User> users = userService.listUsers(page, count);
            return ServerResponse.Success("查询成功", users);
        }
    }

    /*员工编号手机号uid姓名查询用户信息*/

    /*员工手机号查询用户信息*/

}
=======
package team.sxcoding.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Entity.User;
import team.sxcoding.Service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /*uid查询用户信息*/
    @GetMapping("getUserDetailByUid")
    public ServerResponse getUserDetailByUid(Integer uid){
        if (uid == null) {
            return ServerResponse.ErrorMessage("uid为空");
        }
        if(userService.isExistUid(uid)){
            User user = userService.selectUserByUid(uid);
            return ServerResponse.Success("查询成功",user);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    /*员工编号查询用户信息*/
    @GetMapping("getUserDetailByNumber")
    public ServerResponse getUserDetailByNumber(String number){
        if (number.equals(null)) {
            return ServerResponse.ErrorMessage("员工编号为空");
        }
        if(userService.isExistNumber(number)){
            User user = userService.selectUserByNumber(number);
            return ServerResponse.Success("查询成功",user);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    /*手机号查询用户信息*/
    @GetMapping("getUserDetailByTelephone")
    public ServerResponse getUserDetailByTelephone(String telephone){
        if (telephone.equals(null)) {
            return ServerResponse.ErrorMessage("员工编号为空");
        }
        if(userService.isExistTelephone(telephone)){
            User user = userService.selectUserByTelephone(telephone);
            return ServerResponse.Success("查询成功",user);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    /*查看所有用户信息*/
    @GetMapping("listUsers")
    public ServerResponse listUsers(Integer page,Integer count){
        if ( page == null || count == null) {
            return ServerResponse.Error();
        }else {
            PageResult<User> users = userService.listUsers(page, count);
            return ServerResponse.Success("查询成功", users);
        }
    }

    /*员工编号手机号uid姓名查询用户信息*/

}
>>>>>>> 0be10400409c0210cb86ebb1866173f09c6b461e
