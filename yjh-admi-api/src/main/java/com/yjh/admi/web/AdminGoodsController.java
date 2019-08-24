package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.admi.dto.GoodsAllinone;
import com.yjh.admi.dto_helper.GoodsAttributeInfo;
import com.yjh.admi.dto_helper.GoodsInfo;
import com.yjh.admi.dto_helper.GoodsProductInfo;
import com.yjh.admi.dto_helper.GoodsSpecificationInfo;
import com.yjh.admi.service.AdminGoodsService;
import com.yjh.admi.vo.ExtensionAttributeVo;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.AllSpjbxx;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@RestController
@RequestMapping("/admin/goods")
@Validated
public class AdminGoodsController {
    private final Log logger = LogFactory.getLog(AdminGoodsController.class);

    @Autowired
    AdminGoodsService adminGoodsService;
    /**
     * 查询商品
     *
     * @param goodsSn
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("admin:goods:list")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String goodsSn, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "tjsj") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        return adminGoodsService.list(goodsSn, name, page, limit, sort, order);
    }

    @GetMapping("/catAndBrandAndSupplier")
    public Object list2() {
        return adminGoodsService.list2();
    }

    /**
     * 编辑商品
     *
     * @param goodsAllinone
     * @return
     */
    @RequiresPermissions("admin:goods:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "更新")
    @PostMapping("/update")
    public Object update(@RequestBody GoodsAllinone goodsAllinone, HttpSession session) {
        String userName=session.getValue("userName").toString();
        return adminGoodsService.update(goodsAllinone,userName);
    }

    /**
     * 删除商品
     *
     * @param goods
     * @return
     */
    @RequiresPermissions("admin:goods:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody GoodsInfo goods) {
        return adminGoodsService.delete(goods);
    }

    /**
     * 添加商品
     *
     * @param goodsAllinone
     * @return
     */
    @RequiresPermissions("admin:goods:create")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "上架")
    @PostMapping("/create")
    public Object create(@RequestBody GoodsAllinone goodsAllinone, HttpSession session) {
        String userName=session.getValue("userName").toString();
        return adminGoodsService.create(goodsAllinone,userName);
    }

//    /**
//     * 商品详情
//     *
//     * @param id
//     * @return
//     */
    @RequiresPermissions("admin:goods:detail")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "编辑")
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return adminGoodsService.detail(id);

    }

    @GetMapping("/getGoodsAttribute")
    public Object getGoodsAttribute(int categoryId){
        return adminGoodsService.getGoodsAttribute(categoryId);
    }

    /**
     * 得到商品的扩展属性值
     * @param goodsId
     * @return
     */
    @GetMapping("getGoodsAttributeValue")
    public Object getGoodsAttributeValue(int goodsId){
        return adminGoodsService.getGoodsAttributeValue(goodsId);
    }
}
