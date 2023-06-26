package team.sxcoding.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.User;
import team.sxcoding.Service.DepartmentService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;

import static team.sxcoding.Utils.MD5Util.encrypt;
import static team.sxcoding.Utils.PermissionUtil.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    private HttpServletRequest request;


    /*显示自己的用户信息*/
    @GetMapping("getUser")
    public ServerResponse getUser() {
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }

        User user = userService.selectUserByUid(Integer.valueOf(claims.getId()));
        return ServerResponse.Success("查询成功",user);
    }


    /*uid查询用户信息*/
    @GetMapping("getUserDetailByUid")
    public ServerResponse getUserDetailByUid(Integer uid){
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }

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
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }

        if ( number == null ) {
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
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }

        if (telephone == null ) {
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
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }
        if ( page == null || count == null) {
            return ServerResponse.Error();
        }else {
            IPage<User> users = userService.listUsers(page, count);
            return ServerResponse.Success("查询成功", users);
        }
    }

    /*员工编号手机号uid姓名查询用户信息*/
    @GetMapping("getUsersByUid")
    public ServerResponse getUsersByUid(Integer uid,Integer page,Integer count){
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }
        if (uid == null || page == null || count == null) {
            return ServerResponse.Error();
        }else if(userService.isExistUid(uid)){
            IPage<User> users = userService.getUsersByUid(uid, page, count);
            return ServerResponse.Success("查询成功",users);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }
    @GetMapping("getUsersByNumber")
    public ServerResponse getUsersByNumber(String number,Integer page,Integer count){
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }
        if ( number == null || page == null || count == null) {
            return ServerResponse.Error();
        }else if(userService.isExistNumber(number)){
            IPage<User> users = userService.getUsersByNumber( number, page, count);
            return ServerResponse.Success("查询成功",users);
        }else{
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    @GetMapping("getUsersByTelephone")
    public ServerResponse getUsersByTelephone(String telephone,Integer page,Integer count){
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }
        if ( telephone == null || page == null || count == null) {
            return ServerResponse.Error();
        }else if(userService.isExistTelephone(telephone)){
            IPage<User> users = userService.getUsersByTelephone( telephone, page, count);
            return ServerResponse.Success("查询成功",users);
        }else{
            return ServerResponse.ErrorMessage("用户不存在");
            }
    }

    @GetMapping("getUsersByUsername")
    public ServerResponse getUsersByUsername(String username,Integer page,Integer count){
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }
        if ( username == null || page == null || count == null) {
            return ServerResponse.Error();
        }else if(userService.isExistUsername(username)){
            IPage<User> users = userService.getUsersByUsername( username, page, count);
            return ServerResponse.Success("查询成功",users);
        }else{
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }


    /*通过部门查询用户信息*/
    @GetMapping("getUsersByDepartment")
    public ServerResponse getUsersByDepartment(Integer department,Integer page,Integer count) {
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()) {
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            } else {
                return ServerResponse.NeedLogin();
            }
        }
        if ( department == null || page == null || count == null) {
            return ServerResponse.Error();
        } else if(departmentService.isExistDepartment(department)){
            IPage<User>  users = userService.getUsersByDepartment(department, page, count);
            return ServerResponse.Success("查询成功", users);
        }else {
            return ServerResponse.ErrorMessage("部门不存在");
        }
    }

    /*修改用户信息*/
    @PostMapping("updateUser")
    public ServerResponse updateUser(@RequestBody User user) {
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }

        if (!(user.getPassword() == null)) {
            user.setPassword(encrypt(user.getPassword()));
        }
        if (user.getUid() == null) {
            user.setUid(Integer.valueOf(claims.getId()));
        }
        if (user.getPrivilege() == null) {
            user.setPrivilege(claims.getSubject());
        }

        if(!userService.isExistUid(user.getUid())){
            return ServerResponse.ErrorMessage("用户不存在");
        }

        User oldUser = userService.selectUserByUid(user.getUid());
        if (user.getNumber() == null) {
            user.setNumber(oldUser.getNumber());
        }
        if (user.getTelephone() == null) {
            user.setTelephone(oldUser.getTelephone());
        }

        if (user.getDepartment() == null) {
            user.setDepartment(oldUser.getDepartment());
        }

        if(userService.isExistNumber(user.getNumber()) && !( userService.selectUserByNumber(user.getNumber()).getUid().equals(user.getUid()))){
            return ServerResponse.ErrorMessage("员工编号已存在");
        }

        if(userService.isExistTelephone(user.getTelephone()) && !( userService.selectUserByTelephone(user.getTelephone()).getUid().equals(user.getUid()))){
            return ServerResponse.ErrorMessage("电话号码已存在");
        }

        /*查看修改后的部门是否存在*/
        if(departmentService.isExistDepartment(user.getDepartment())){
            return ServerResponse.ErrorMessage("部门不存在");
        }

        if(isUpdatePermissionIllegal(claims,user,oldUser)){
            return ServerResponse.Forbidden();
        }else if(claims.getId().equals(user.getUid().toString()) && !claims.getSubject().equals(user.getPrivilege())){
            userService.updateUser(user);
            return ServerResponse.SuccessMessage("修改成功,请重新登录");
        }else {
            userService.updateUser(user);
            return ServerResponse.SuccessMessage("修改成功");
        }


    }

    /*通过uid删除用户*/
    @GetMapping("deleteUserByUid")
    public ServerResponse deleteUserByUid(Integer uid) {
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }
        if(!userService.isExistUid(uid)){
            return ServerResponse.ErrorMessage("用户不存在");
        }
        User user = userService.selectUserByUid(uid);
        if(!isDeletePermissionLegal(claims,user)){
            return ServerResponse.Forbidden();
        }else if(userService.deleteUserByUid(uid)){
            return ServerResponse.SuccessMessage("删除成功");
        }else{
            return ServerResponse.ErrorMessage("删除失败");
        }
    }



}

