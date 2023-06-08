package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Unit;
import team.sxcoding.Service.UnitService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UnitService unitService;

    /*查询单位*/
    @GetMapping("getUnit")
    public ServerResponse getUnit(){
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

        List<Unit> units = unitService.selectUnits();
        return ServerResponse.Success("查询成功", units);
    }


    /*增加单位*/
    @PostMapping("insertUnit")
    public ServerResponse insertUnit(@RequestBody Unit unit){
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

        if(unit.getName() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(unitService.isExistUnitName(unit.getName())){
            return ServerResponse.ErrorMessage("单位名重复");
        }else if(unitService.insertUnit(unit)){
            return ServerResponse.Success(unitService.selectUnitIdAndName());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    /*删除单位*/
    @GetMapping("deleteUnitById")
    public ServerResponse deleteUnitById(Integer id){
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
        }else if(!unitService.isExistUnitId(id)){
            return ServerResponse.ErrorMessage("单位不存在");
        }else if(unitService.deleteUnitById(id)){
            return ServerResponse.Success(unitService.selectUnitIdAndName());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    /*修改单位*/
    @PostMapping("updateUnit")
    public ServerResponse updateUnit(@RequestBody Unit unit){
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
        if(unit.getId() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!unitService.isExistUnitId(unit.getId())){
            return ServerResponse.ErrorMessage("单位不存在");
        }else if(unitService.updateUnitById(unit)){
            return ServerResponse.Success(unitService.selectUnitIdAndName());
        }else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }
}
