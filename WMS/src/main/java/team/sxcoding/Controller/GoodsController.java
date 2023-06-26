package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Goods;
import team.sxcoding.Service.*;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ClassService classService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private UnitService unitService;

    @GetMapping("getGoods")
    public ServerResponse getGoods(@RequestBody Goods goods, Integer page, Integer count) {
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

        return ServerResponse.Success("查询成功", goodsService.selectGoods(goods, page, count));
    }


    @GetMapping("getGoodsByInboundTime")
    public ServerResponse getGoodsByInboundTime(Goods goods, Integer page, Integer count, LocalDateTime start, LocalDateTime end) {
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

        return ServerResponse.Success("查询成功", goodsService.selectGoodsByInboundTime(goods, start, end, page, count));
    }

    @GetMapping("getGoodsByOutboundTime")
    public ServerResponse getGoodsByOutboundTime(Goods goods, Integer page, Integer count, LocalDateTime start, LocalDateTime end) {
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

        return ServerResponse.Success("查询成功", goodsService.selectGoodsByOutboundTime(goods, start, end, page, count));
    }

    @PostMapping("insertGoods")
    public ServerResponse insertGoods(@RequestBody Goods goods) {
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

        if (!isWarehousekeeper(claims)) {
            return ServerResponse.Forbidden();
        }

        if (goods.getName() == null || goods.getCount() == null || goods.getPrice() == null || goods.getValue() == null || goods.getClassUid() == null || goods.getGroupUid() == null || goods.getWarehouseUid() == null || goods.getUnitUid() == null) {
            return ServerResponse.ErrorMessage("必填字段未填写");
        } else if (!(groupService.isExistGroupId(goods.getGroupUid()) && classService.isExistClassId(goods.getClassUid()) && unitService.isExistUnitId(goods.getUnitUid()) && warehouseService.isExistWarehouse(goods.getWarehouseUid()))) {
            return ServerResponse.ErrorMessage("部分数据不存在");
        } else {
            goodsService.saveOrUpdateGoods(goods);
            return ServerResponse.Success();
        }
    }


    @GetMapping("deleteGoods")
    public ServerResponse deleteGoods(String uid){
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

        if( uid == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!goodsService.isExistGoods(uid)){
            return ServerResponse.ErrorMessage("货物不存在");
        }else if(goodsService.deleteGoodsById(uid)){
            return ServerResponse.Success("删除成功",goodsService.selectGoods());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }


    @PostMapping("updateGoods")
    public ServerResponse updateGoods(@RequestBody Goods goods){
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

        if(goods.getUid() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!goodsService.isExistGoods(goods.getUid())){
            return ServerResponse.ErrorMessage("货物不存在");
        }else if(goodsService.saveOrUpdateGoods(goods)){
            return ServerResponse.Success(goodsService.selectGoodsById(goods.getUid()));
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }



}
