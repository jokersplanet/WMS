package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Entity.Warehouse;
import team.sxcoding.Service.GoodsService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Service.WarehouseService;

import java.math.BigDecimal;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/warehouse")
public class WarehouseController {



    @Autowired
    UserService userService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GoodsService goodsService;


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
            return ServerResponse.Success("查询成功", warehouseService.selectWarehouse(page, count));
        }

    }

    /*创建仓库*/
    @PostMapping("insertWarehouse")
    public ServerResponse insertWarehouse(@RequestBody Warehouse warehouse){
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

        warehouse.setValue(BigDecimal.ZERO);

        if(warehouse.getName() == null || warehouse.getHead() == null || warehouse.getAddress() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(warehouseService.isExistWarehouseName(warehouse.getName())){
            return ServerResponse.ErrorMessage("仓库名重复");
        }else{
            warehouseService.saveOrUpdateWarehouse(warehouse);
            return ServerResponse.Success();
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

        if(!isWarehousekeeper(claims)){
            return ServerResponse.Forbidden();
        }

        if(warehouse.getUid() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!warehouseService.isExistWarehouse(warehouse.getUid())) {
            return ServerResponse.ErrorMessage("仓库不存在");
        }else if(warehouseService.saveOrUpdateWarehouse(warehouse)){
            return ServerResponse.Success(warehouseService.selectWarehouseById(warehouse.getUid()));
        }else{
            return ServerResponse.ErrorMessage("操作失败");
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

        if(!isAdmin(claims)){
            return ServerResponse.Forbidden();
        }

        if(id == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!warehouseService.isExistWarehouse(id)){
            return ServerResponse.ErrorMessage("仓库不存在");
        }else if(goodsService.isExistWarehouse(id)) {
            return ServerResponse.ErrorMessage("该类下存在货物无法删除");
        }else if(warehouseService.deleteWarehouse(id)){
            return ServerResponse.Success(warehouseService.selectWarehouse());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }


}
