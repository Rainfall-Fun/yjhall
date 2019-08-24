package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.admi.dto.GoodsAllinone;
import com.yjh.admi.vo.CatVo;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.AllGys;
import com.yjh.db.domain.AllYwqy;
import com.yjh.db.domain.SysYh;
import com.yjh.db.service.BusinessRegionService;
import com.yjh.db.service.SupplierService;
import com.yjh.db.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by 彭椿悦 on 2019/7/31 13:00
 */
@RestController
@RequestMapping("/admin/supplier")
public class AdminSupplierController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    BusinessRegionService businessRegionService;
    @Autowired
    UserService userService;

    /**
     * 添加供应商
     * @param supplier
     * @param session
     * @return
     */
    @RequiresPermissions("admin:supplier:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "供应商管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody AllGys supplier) {
        Subject currentUser = SecurityUtils.getSubject();
        SysYh user = (SysYh) currentUser.getPrincipal();
        String userName=user.getYhm();
        if(supplier.getGysmc()==null||supplier.getGysmc().equals(""))
            return ResponseUtil.badArgument();
        LocalDateTime ldt = LocalDateTime.now();
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();

        String supplierMemberId=user.getYhbm()+d.format(date);
        supplier.setTjr(userName);//设置添加人
        supplier.setTjsj(ldt);//添加时间
        supplier.setZhxgr(userName);//设置最后添加人
        supplier.setZhxgsj(ldt);//设置最后添加时间
        supplier.setGyshybh(supplierMemberId);//供应商会员编号
        supplier.setGysbm(UUID.randomUUID().toString().replaceAll("-",""));//色湖之供应商编码
        supplierService.addSupplier(supplier);
        return ResponseUtil.ok(supplier);
    }

    @RequiresPermissions("admin:supplier:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "供应商管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "tjsj") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        return ResponseUtil.okList(supplierService.querySelective(id, name, page, limit, sort, order));
    }
    @GetMapping("/getAreaList")
    public Object list2() {
        //管理员设置“所属分类”
        List<AllYwqy> regionL1 = businessRegionService.getAllL1Area();
        List<CatVo> catVoListL1=new ArrayList<>(regionL1.size());

        //添加一级区域
        for (AllYwqy r1 :
                regionL1) {
            CatVo l1CatVo = new CatVo();
            l1CatVo.setValue(r1.getYwqybm());
            l1CatVo.setLabel(r1.getQymc());
            //添加二级二级
            List<AllYwqy> regionL2 = businessRegionService.getAllL2AreaBySuperiorArea(r1.getYwqybm());
            List<CatVo> catVoListL2=new ArrayList<>(regionL2.size());
            for (AllYwqy r2 :
                    regionL2) {
                CatVo l2CatVo=new CatVo();
                l2CatVo.setValue(r2.getYwqybm());
                l2CatVo.setLabel(r2.getQymc());
                //添加三级区域
                List<AllYwqy> regionL3 = businessRegionService.getAllL3AreaBySuperiorArea(r2.getYwqybm());
                List<CatVo> catVoListL3=new ArrayList<>(regionL3.size());
                for (AllYwqy r3 :
                        regionL3) {
                    CatVo l3CateVo = new CatVo();
                    l3CateVo.setValue(r3.getYwqybm());
                    l3CateVo.setLabel(r3.getQymc());
                    catVoListL3.add(l3CateVo);
                }
                l2CatVo.setChildren(catVoListL3);
                catVoListL2.add(l2CatVo);
            }
            l1CatVo.setChildren(catVoListL2);
            catVoListL1.add(l1CatVo);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("areaList", catVoListL1);//区域list
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:supplier:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "供应商管理"}, button = "删除")
    @PostMapping("/delete")
    public Object deleteSupplier(@RequestBody AllGys supplier){
        if (supplierService.deleteSupplier(supplier))
            return ResponseUtil.ok();
        else
            return ResponseUtil.fail(1,"删除失败");
    }

    /**
     * 更新供应商信息
     * @param supplier
     * @return
     */
    @RequiresPermissions("admin:supplier:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "供应商管理"}, button = "修改")
    @PostMapping("/update")
    public Object update(@RequestBody AllGys supplier) {
        Object error = validate(supplier);
        if (error != null) {
            return error;
        }
        if (supplierService.updateSupplier(supplier)!=1) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(supplier);
    }

    private Object validate(AllGys supplier) {
        String name = supplier.getGysmc();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }
}
