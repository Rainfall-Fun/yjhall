package com.yjh.admi.web;

import com.yjh.core.util.ResponseUtil;
import com.yjh.db.service.GoodsService;
import com.yjh.db.service.OrderService;
import com.yjh.db.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@RestController
@RequestMapping("/admin/dashboard")
@Validated
public class AdminDashbordController {
    private final Log logger = LogFactory.getLog(AdminDashbordController.class);

    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @GetMapping("")
    public Object info() {
        long userTotal = userService.countUsers();
        int goodsTotal = (int) goodsService.countGoods();
        //int productTotal = goodsService.getAllGoods();
        int orderTotal = (int) orderService.countOrders();
        Map<String, Integer> data = new HashMap<>();
        data.put("userTotal", (int) userTotal);
        data.put("goodsTotal", goodsTotal);
        data.put("productTotal", 10);
        data.put("orderTotal", orderTotal);
        return ResponseUtil.ok(data);
    }

}
