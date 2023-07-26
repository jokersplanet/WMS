package team.sxcoding.Controller;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Order;
import team.sxcoding.Service.OrderService;
import team.sxcoding.Service.StatusService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;


import static team.sxcoding.Utils.PermissionUtil.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StatusService statusService;


    @GetMapping("getOrder")
    public ServerResponse getOrder(Integer uid, String goodsName ,String status, Integer page, Integer count) {
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

        return ServerResponse.Success("查询成功", orderService.selectOrder(uid,goodsName,status, page, count));

    }


    @GetMapping("getOrderByTime")
    public ServerResponse getOrderByTime(Integer uid, String goodsName,String status , String startTime, String endTime , Integer page, Integer count) {
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

        return ServerResponse.Success("查询成功",orderService.selectOrderByTime(uid,goodsName,status,startTime,endTime, page, count));
    }


    @PostMapping("insertOrder")
    public ServerResponse insertOrder(@RequestBody Order order) {
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

        if (order.getGoodsName() == null || order.getCount() == null || order.getPrice() == null || order.getValue() == null || order.getStatus() == null) {
            return ServerResponse.ErrorMessage("必填字段未填写");
        } else if (!statusService.isExistStatus(order.getStatus())){
            return ServerResponse.ErrorMessage("部分数据不存在");
        }else{
            orderService.insertOrder(order);
            return ServerResponse.Success(orderService.selectOrderLimit());
        }
    }


    @GetMapping("deleteOrder")
    public ServerResponse deleteOrder(Integer uid){
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
        }else if(!orderService.isExistOrder(uid)){
            return ServerResponse.ErrorMessage("订单不存在");
        }else if(orderService.deleteOrderById(uid)){
            return ServerResponse.Success("删除成功",orderService.selectOrder(0,-1));
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }


    @PostMapping("updateOrder")
    public ServerResponse updateOrder(@RequestBody Order order) {
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

        if (order.getUid() == null) {
            return ServerResponse.ErrorMessage("必填字段未填写");
        } else if (!orderService.isExistOrder(order.getUid())) {
            return ServerResponse.ErrorMessage("订单不存在");
        } else if (orderService.updateOrder(order)) {
            return ServerResponse.Success(orderService.selectOrderById(order.getUid()));
        } else {
            return ServerResponse.ErrorMessage("操作失败");
        }
    }
}
