package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.PermissionLevel;
import team.sxcoding.Entity.Department;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Entity.Warehouse;
import team.sxcoding.Service.DepartmentService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Service.WarehouseService;

import java.math.BigDecimal;
import java.util.List;

import static team.sxcoding.Utils.PermissionUtil.getToken;
import static team.sxcoding.Utils.PermissionUtil.isJwtLegal;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {



    @Autowired
    UserService userService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    private HttpServletRequest request;


    /*查询所有仓库*/
    @GetMapping("selectWarehouse")
    public ServerResponse selectWarehouse(Integer page,Integer count) {
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
        if (page == null || count == null) {
            return ServerResponse.Error();
        } else {
            PageResult<Warehouse> warehouse = warehouseService.selectWarehouse(page, count);
            return ServerResponse.Success("查询成功", warehouse);
        }

    }

    /*创建仓库*/
    @PostMapping("insertWarehouse")
    public ServerResponse createWarehouse(@RequestBody Warehouse warehouse){
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
        if(!(claims.getSubject().equals(PermissionLevel.SUPER_ADMIN)||claims.getSubject().equals(PermissionLevel.ADMIN))){
            return ServerResponse.Forbidden();
        }

        warehouse.setValue(BigDecimal.ZERO);

        if(warehouse.getName() == null || warehouse.getHead() == null || warehouse.getAddress() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else {
            List<Warehouse> warehouseList = warehouseService.createWarehouse(warehouse);
            return ServerResponse.Success(warehouseList);
        }
    }

    /*修改仓库*/
    @PostMapping("updateWarehouse")
    public ServerResponse updateWarehouse(@RequestBody Warehouse warehouse) {
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

        if(!(claims.getSubject().equals(PermissionLevel.SUPER_ADMIN)||claims.getSubject().equals(PermissionLevel.ADMIN))){
            return ServerResponse.Forbidden();
        }

        if(warehouse.getId() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else {
            List<Warehouse> warehouseList = warehouseService.updateWarehouse(warehouse);
            return ServerResponse.Success(warehouseList);
        }
    }

    /*删除仓库*/
    @GetMapping("deleteWarehouseById")
    public ServerResponse deleteWarehouseById(Integer id){
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

        if(!(claims.getSubject().equals(PermissionLevel.SUPER_ADMIN)||claims.getSubject().equals(PermissionLevel.ADMIN))){
            return ServerResponse.Forbidden();
        }

        if(!warehouseService.isExistWarehouse(id)){
            return ServerResponse.ErrorMessage("仓库不存在");
        }

        if(id == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else{
            List<Warehouse> warehouseList = warehouseService.deleteWarehouse(id);
            return ServerResponse.Success(warehouseList);
        }
    }


}
