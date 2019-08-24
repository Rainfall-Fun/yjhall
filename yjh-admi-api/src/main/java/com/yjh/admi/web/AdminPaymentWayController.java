package com.yjh.admi.web;

import com.alibaba.druid.util.StringUtils;
import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.AllZffs;
import com.yjh.db.service.PaymentWayService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/1 10:26
 */
@RestController
@RequestMapping("/admin/paymentWay")
@Validated
public class AdminPaymentWayController {
    private final Log logger = LogFactory.getLog(AdminBrandController.class);

    @Autowired
    private PaymentWayService paymentWayService;

    @RequiresPermissions("admin:paymentWay:list")
    @RequiresPermissionsDesc(menu = {"配置管理", "支付方式配置"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "zffsbm") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<AllZffs> brandList = paymentWayService.querySelective(id, name, page, limit, sort, order);
        return ResponseUtil.okList(brandList);
    }

    private Object validate(AllZffs paymentWay) {
        String name = paymentWay.getZffs();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        if (paymentWayService.checkPaymentWay(name)&&paymentWay.getZffsbm()==null)
            return ResponseUtil.fail(1,"已存在该支付方式");
        return null;
    }

    @RequiresPermissions("admin:paymentWay:create")
    @RequiresPermissionsDesc(menu = {"配置管理", "支付方式配置"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody AllZffs paymentWay) {
        Object error = validate(paymentWay);
        if (error != null) {
            return error;
        }
        int result = paymentWayService.addPaymentWay(paymentWay);
        if (result==0){
            return ResponseUtil.fail(1,"添加商品品牌信息失败");
        }
        paymentWay.setZffsbm(result);
        return ResponseUtil.ok(paymentWay);
    }
//    @RequiresPermissions("admin:brand:read")
//    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "详情")
//    @GetMapping("/read")
//    public Object read(@NotNull Integer id) {
//        LitemallBrand brand = brandService.findById(id);
//        return ResponseUtil.ok(brand);
//    }

    @RequiresPermissions("admin:paymentWay:update")
    @RequiresPermissionsDesc(menu = {"配置管理", "支付方式配置"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody AllZffs paymentWay) {
        Object error = validate(paymentWay);
        if (error != null) {
            return error;
        }
        if (paymentWayService.updatePaymentWay(paymentWay)) {
            return ResponseUtil.ok(paymentWay);
        }
        return ResponseUtil.fail(1,"更新失败请联系网站管理员");
    }


    @RequiresPermissions("admin:paymentWay:delete")
    @RequiresPermissionsDesc(menu = {"配置管理", "支付方式配置"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody AllZffs paymentWay) {
        Integer id = paymentWay.getZffsbm();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        if (paymentWayService.deletePayment(id)) {
            return ResponseUtil.ok();
        }else
            return ResponseUtil.fail(1,"删除失败请联系网站管理员");
    }
}
