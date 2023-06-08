package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Department;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Service.DepartmentService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    private HttpServletRequest request;

    /*查询部门*/
    @GetMapping("selectDepartment")
    public ServerResponse selectDepartment(Integer page,Integer count){
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
            PageResult<Department> departments = departmentService.selectDepartment( page, count);
            return ServerResponse.Success("查询成功", departments);
        }

    }

    /*创建部门*/
    @PostMapping("insertDepartment")
    public ServerResponse createDepartment(@RequestBody Department department){
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

        if(department.getName() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(departmentService.isExistDepartmentName(department.getName())) {
            return ServerResponse.ErrorMessage("部门名重复");
        }else if(departmentService.insertDepartment(department)){
            return ServerResponse.Success(departmentService.selectDepartmentIdAndName());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    /*修改部门*/
    @PostMapping("updateDepartment")
    public ServerResponse updateDepartment(@RequestBody Department department){
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

        if(department.getId() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!departmentService.isExistDepartment(department.getId())){
            return ServerResponse.ErrorMessage("部门不存在");
        }else if(departmentService.updateDepartment(department)){
            return ServerResponse.Success(departmentService.selectDepartmentIdAndName());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }

    }

    /*删除部门*/
    @GetMapping("deleteDepartment")
    public ServerResponse<List<Department>> deleteDepartment(Integer id){
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

        if(id == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!departmentService.isExistDepartment(id)){
            return ServerResponse.ErrorMessage("部门不存在");
        }else if(departmentService.deleteDepartment(id)){
            return ServerResponse.Success(departmentService.selectDepartmentIdAndName());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }

    }



}
