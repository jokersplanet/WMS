package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Group;
import team.sxcoding.Service.ClassService;
import team.sxcoding.Service.GroupService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ClassService classService;

    /*查询类别*/
    @GetMapping("getGroup")
    public ServerResponse getGroup(){
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
        List<Group> Groups = groupService.selectGroups();
        return ServerResponse.Success("查询成功", Groups);
    }


    /*增加类别*/
    @PostMapping("insertGroup")
    public ServerResponse insertGroup(@RequestBody Group group){
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

        if(group.getName() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(groupService.isExistGroupName(group.getName())) {
            return ServerResponse.ErrorMessage("类别名重复");
        }else if(groupService.insertGroup(group)){
            return ServerResponse.Success(groupService.selectGroupIdAndName());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    /*删除类别*/
    /*判断大类别下小类别，如果有小类别需要先删除所有小类别*/
    @GetMapping("deleteGroupById")
    public ServerResponse deleteGroupById(Integer id){
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

        if( id == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!groupService.isExistGroupId(id)){
            return ServerResponse.ErrorMessage("类别不存在");
        }else if(classService.deleteClassByGroupId(id) && groupService.deleteGroupById(id) || !classService.deleteClassByGroupId(id) && groupService.deleteGroupById(id)){
            return ServerResponse.Success(groupService.selectGroupIdAndName());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    /*修改类别*/
    @PostMapping("updateGroup")
    public ServerResponse updateGroup(@RequestBody Group group){
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

        if(group.getId() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!groupService.isExistGroupId(group.getId())){
            return ServerResponse.ErrorMessage("类别不存在");
        }else if(groupService.updateGroupById(group)){
            return ServerResponse.Success(groupService.selectGroupIdAndName());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

}
