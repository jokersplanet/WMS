package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Status;
import team.sxcoding.Service.OrderService;
import team.sxcoding.Service.StatusService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StatusService statusService;

    @Autowired
    private OrderService orderService;


    /*查询状态*/
    @GetMapping("getStatus")
    public ServerResponse getStatus(){
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
        return ServerResponse.Success("查询成功", statusService.selectStatus());
    }


    /*增加状态*/
    @PostMapping("insertStatus")
    public ServerResponse insertStatus(@RequestBody Status status){
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
        if(!isAdmin(claims)){
            return ServerResponse.Forbidden();
        }

        if(status.getName() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(statusService.isExistStatus(status.getName())){
            return ServerResponse.ErrorMessage("订单状态重复");
        }else if(statusService.saveOrUpdateStatus(status)){
            return ServerResponse.Success(status);
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    /*删除状态*/
    @GetMapping("deleteStatusById")
    public ServerResponse deleteStatusById(Integer uid){
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
        if(!isAdmin(claims)){
            return ServerResponse.Forbidden();
        }

        if( uid == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!statusService.isExistStatus(uid)){
            return ServerResponse.ErrorMessage("状态不存在");
        }else if( orderService.isExistStatus(uid)) {
            return ServerResponse.ErrorMessage("该状态下存在订单无法删除");
        }else if(statusService.deleteStatusById(uid)){
            return ServerResponse.Success(statusService.selectStatus());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    /*修改单位*/
    @PostMapping("updateStatus")
    public ServerResponse updateStatus(@RequestBody Status status){
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
        if(!isAdmin(claims)){
            return ServerResponse.Forbidden();
        }
        if(status.getName() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!statusService.isExistStatus(status.getUid())){
            return ServerResponse.ErrorMessage("状态不存在");
        }else if(statusService.saveOrUpdateStatus(status)){
            return ServerResponse.Success(status);
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }
}

