package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Class;
import team.sxcoding.Service.ClassService;
import team.sxcoding.Service.GroupService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClassService classService;

    @Autowired
    private GroupService groupService;

    @GetMapping("getClassByGroupId")
    public ServerResponse getClassByGroupId(Integer groupid){
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

        List<Class> classes = classService.selectClassByGroupId(groupid);
        return ServerResponse.Success("查询成功", classes);
    }


    @PostMapping("insertClass")
    public ServerResponse insertClass(@RequestBody Class clazz){
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

        if(!isWarehousekeeper(claims)){
            return ServerResponse.Forbidden();
        }

        if(clazz.getName() == null || clazz.getGroupId() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(groupService.isExistGroupId(clazz.getGroupId() )){
           return ServerResponse.ErrorMessage("大类不存在");
        }else if(classService.isExistClassName(clazz.getName(),clazz.getGroupId())){
            return ServerResponse.ErrorMessage("该类已存在");
        }else if(classService.insertClassByGroupId(clazz)){
            return ServerResponse.Success(classService.selectClassIdAndName());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @PostMapping("updateClass")
    public ServerResponse updateClass(@RequestBody Class clazz) {
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
        if(!isWarehousekeeper(claims)){
            return ServerResponse.Forbidden();
        }
        if(clazz.getId() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!classService.isExistClassId(clazz.getId())){
            return ServerResponse.ErrorMessage("类别不存在");
        }else if(classService.updateClassById(clazz)){
            return ServerResponse.Success(classService.selectClassIdAndName());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @GetMapping("deleteClassById")
    public ServerResponse deleteClass(Integer id){
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
        if(!isWarehousekeeper(claims)){
            return ServerResponse.Forbidden();
        }
        if( id == null ){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!classService.isExistClassId(id)){
            return ServerResponse.ErrorMessage("类别不存在");
        }else if(classService.deleteClassById(id)){
            return ServerResponse.Success(classService.selectClassIdAndName());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }


    @GetMapping("deleteClassByGroupId")
    public ServerResponse deleteClassById(Integer groupId) {
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
        if(!isWarehousekeeper(claims)) {
            return ServerResponse.Forbidden();
        }
        if(groupId == null) {
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if (!groupService.isExistGroupId(groupId)) {
            return ServerResponse.ErrorMessage("类别不存在");
        }else if(classService.deleteClassByGroupId(groupId)){
            return ServerResponse.Success(classService.selectClassIdAndName());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }
}