package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Entity.User;
import team.sxcoding.Service.UserService;
import team.sxcoding.Utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;

import static team.sxcoding.Utils.MD5Util.encrypt;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private HttpServletRequest request;


    /*显示自己的用户信息*/
    @GetMapping("getUser")
    public ServerResponse getUser() {
        String jwt = null;
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
        }
        Jws<Claims> claimsJws  = JwtUtil.parseToken(jwt);
        User user = userService.selectUserByUid(Integer.valueOf(claimsJws.getBody().getId()));
        return ServerResponse.Success("查询成功",user);
    }

    /*uid查询用户信息*/
    @GetMapping("getUserDetailByUid")
    public ServerResponse getUserDetailByUid(Integer uid){
        if (uid == null) {
            return ServerResponse.ErrorMessage("uid未填写");
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
            return ServerResponse.ErrorMessage("员工编号未填写");
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
            return ServerResponse.ErrorMessage("电话号码未填写");
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
    @GetMapping("getUsersByUid")
    public ServerResponse getUsersByUid(Integer uid,Integer page,Integer count){
        if (uid == null || page == null || count == null) {
            return ServerResponse.Error();
        }else {
            PageResult<User> users = userService.getUsersByUid(uid, page, count);
            return ServerResponse.Success("查询成功",users);
        }
    }
    @GetMapping("getUsersByNumber")
    public ServerResponse getUsersByNumber(String number,Integer page,Integer count){
        if ( number == null || page == null || count == null) {
            return ServerResponse.Error();
        }else {
            PageResult<User> users = userService.getUsersByNumber( number, page, count);
            return ServerResponse.Success("查询成功",users);
        }
    }
    @GetMapping("getUsersByTelephone")
    public ServerResponse getUsersByTelephone(String telephone,Integer page,Integer count){
        if ( telephone == null || page == null || count == null) {
            return ServerResponse.Error();
        }else {
            PageResult<User> users = userService.getUsersByTelephone( telephone, page, count);
            return ServerResponse.Success("查询成功",users);
        }
    }
    @GetMapping("getUsersByName")
    public ServerResponse getUsersByName(String username,Integer page,Integer count){
        if ( username == null || page == null || count == null) {
            return ServerResponse.Error();
        }else {
            PageResult<User> users = userService.getUsersByName( username, page, count);
            return ServerResponse.Success("查询成功",users);
        }
    }

    /*修改用户信息*/
    @PostMapping("updateUser")
    public ServerResponse updateUser(@RequestBody User user){

        String jwt = null;
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
        }
        Jws<Claims> claimsJws  = JwtUtil.parseToken(jwt);
        if(!(user.getPassword().equals(null))){
            user.setPassword(encrypt(user.getPassword()));
        }
        if(user.getUid().equals(null)){
            user.setUid(Integer.valueOf(claimsJws.getBody().getId()));
        }

        if(claimsJws.getBody().getSubject().equals("0")){
            //修改呗，修改所有的人除了uid所有的东西
            userService.updateUser(user);
            return ServerResponse.SuccessMessage("修改成功");
        }else if(claimsJws.getBody().getSubject().equals("3")){
            //修改所有人（除了超级管理员）所有信息除了权限等级改为0
            if(user.getPrivilege().equals(0)){
                return ServerResponse.Forbidden();
            }
            if(userService.selectUserByUid(user.getUid()).getPrivilege().equals("0")){
                return ServerResponse.Forbidden();
            }
            userService.updateUser(user);
            return ServerResponse.SuccessMessage("修改成功");
        }else{
            //修改自己的信息 权限等级 员工编号不可以修改
            if(!(user.getUid().equals(Integer.valueOf(claimsJws.getBody().getId())))){
                return ServerResponse.Forbidden();
            }
            if(user.getPrivilege().equals(null)&&user.getNumber().equals(null)){
                userService.updateUser(user);
                return ServerResponse.SuccessMessage("修改成功");
            }
                return ServerResponse.Forbidden();
        }

    }

}

