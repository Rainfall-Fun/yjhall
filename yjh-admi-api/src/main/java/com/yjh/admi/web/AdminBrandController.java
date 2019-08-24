package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.AllSppp;
import com.yjh.db.service.GoodsBrandService;
import com.yjh.db.service.SupplierService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/brand")
@Validated
public class AdminBrandController {
    private final Log logger = LogFactory.getLog(AdminBrandController.class);

    @Autowired
    private GoodsBrandService brandService;

    @RequiresPermissions("admin:brand:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "zhxgsj") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<AllSppp> brandList = brandService.querySelective(id, name, page, limit, sort, order);
        return ResponseUtil.okList(brandList);
    }

    private Object validate(AllSppp brand) {
        String name = brand.getPpmc();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        Integer sfxs = brand.getSfxs();
        if (sfxs==null) {
            return ResponseUtil.badArgument();
        }
        if (brandService.checkBrandName(brand.getPpmc()))
            return ResponseUtil.fail(1,"已存在该品牌");
        return null;
    }

    @RequiresPermissions("admin:brand:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody AllSppp brand,HttpSession session) {
        Object error = validate(brand);
        if (error != null) {
            return error;
        }
        String userName = session.getValue("userName").toString();
        LocalDateTime ldt = LocalDateTime.now();
        brand.setTjr(userName);//添加人
        brand.setTjsj(ldt);//添加时间
        brand.setZhxgr(userName);//最后修改人
        brand.setZhxgsj(ldt);//最后修改时间
        int result = brandService.addBrandInfo(brand);
        if (result==0){
            return ResponseUtil.fail(1,"添加商品品牌信息失败");
        }
        brand.setSpppbm(result);
        return ResponseUtil.ok(brand);
    }

//    @RequiresPermissions("admin:brand:read")
//    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "详情")
//    @GetMapping("/read")
//    public Object read(@NotNull Integer id) {
//        LitemallBrand brand = brandService.findById(id);
//        return ResponseUtil.ok(brand);
//    }
    @RequiresPermissions("admin:brand:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody AllSppp brand, HttpSession session) {
        Object error = validate(brand);
        if (error != null) {
            return error;
        }
        String userName = session.getValue("userName").toString();
        LocalDateTime ldt = LocalDateTime.now();

        brand.setZhxgr(userName);//设置最后修改人
        brand.setZhxgsj(ldt);//设置最后修改时间
        if (brandService.updateBrandInfo(brand) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:brand:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "品牌管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody AllSppp brand) {
        Integer id = brand.getSpppbm();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        if (brandService.deleteBrand(id)!=1) {
            return ResponseUtil.badArgument();
        }else
            return ResponseUtil.ok();
    }

}
