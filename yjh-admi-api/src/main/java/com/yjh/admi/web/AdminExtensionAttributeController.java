package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.admi.vo.CatVo;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.AllKzsx;
import com.yjh.db.domain.AllSpfl;
import com.yjh.db.service.ExtensionAttributeService;
import com.yjh.db.service.GoodsCategoryExtensionService;
import com.yjh.db.service.GoodsCategoryService;
import com.yjh.db.service.GoodsExtensionAttributeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 彭椿悦 on 2019/8/4 17:09
 */
@RestController
@RequestMapping("/admin/extensionAttribute")
@Validated
public class AdminExtensionAttributeController {

    @Autowired
    GoodsCategoryService goodsCategoryService;
    @Autowired
    GoodsExtensionAttributeService goodsExtensionAttributeService;
    @Autowired
    GoodsCategoryExtensionService goodsCategoryExtensionService;
    @Autowired
    ExtensionAttributeService extensionAttributeService;

    @RequiresPermissions("admin:extensionAttribute:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "扩展属性"}, button = "查询")
    @GetMapping("/list")
    public Object list(String id, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "kzsxbm") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<AllKzsx> brandList = extensionAttributeService.querySelective(id, name, page, limit, sort, order);
        return ResponseUtil.okList(brandList);
    }
    @GetMapping("/cat")
    public Object cat(){
        List<AllSpfl> categoryL1 = goodsCategoryService.getGoodsL1Categories();
        List<CatVo> catVoListL1=new ArrayList<>(categoryL1.size());
        //添加一级分类
        for (AllSpfl goodsCategoryL1 :
                categoryL1) {
            CatVo l1CatVo = new CatVo();
            l1CatVo.setValue(goodsCategoryL1.getSpflbm());
            l1CatVo.setLabel(goodsCategoryL1.getSpfl());
            //添加二级分类
            List<AllSpfl> categoryL2 = goodsCategoryService.getGoodsL2CategoriesBySuperiorCategoryId(goodsCategoryL1.getSpflbm());
            List<CatVo> catVoListL2=new ArrayList<>(categoryL2.size());
            for (AllSpfl goodsCategoryL2 :
                    categoryL2) {
                CatVo l2CatVo=new CatVo();
                l2CatVo.setValue(goodsCategoryL2.getSpflbm());
                l2CatVo.setLabel(goodsCategoryL2.getSpfl());
                //添加三级分类
                List<AllSpfl> categoryL3 = goodsCategoryService.getGoodsL3CategoriesBySuperiorCategoryId(goodsCategoryL2.getSpflbm());
                List<CatVo> catVoListL3=new ArrayList<>(categoryL3.size());
                for (AllSpfl goodsCategoryL3 :
                        categoryL3) {
                    CatVo l3CateVo = new CatVo();
                    l3CateVo.setValue(goodsCategoryL3.getSpflbm());
                    l3CateVo.setLabel(goodsCategoryL3.getSpfl());
                    catVoListL3.add(l3CateVo);
                }
                l2CatVo.setChildren(catVoListL3);
                catVoListL2.add(l2CatVo);
            }
            l1CatVo.setChildren(catVoListL2);
            catVoListL1.add(l1CatVo);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", catVoListL1);//分类list
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:extensionAttribute:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "扩展属性"}, button = "更新")
    @PostMapping("/update")
    public Object update(@RequestBody AllKzsx extensionAttribute) {
        boolean update = extensionAttributeService.update(extensionAttribute);
        return update?ResponseUtil.ok(extensionAttribute):ResponseUtil.fail(1,"更新失败，请重试，不行联系管理员");
    }

    @RequiresPermissions("admin:extensionAttribute:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "扩展属性"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody AllKzsx extensionAttribute) {
        Integer extensionAttributeId = extensionAttribute.getKzsxbm();
        boolean delete = extensionAttributeService.delete(extensionAttributeId);
        //删除商品类别扩展属性中包含此扩展属性id的记录
        goodsCategoryExtensionService.deleteByExtensionAttributeId(extensionAttributeId);
        //删除商品扩展属性中半酣此扩展属性id的记录
        goodsExtensionAttributeService.deleteByExtensionAttributeId(extensionAttributeId);
        return delete?ResponseUtil.ok(extensionAttribute):ResponseUtil.fail(1,"更新失败，请重试，不行联系管理员");
    }

    @RequiresPermissions("admin:extensionAttribute:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "扩展属性"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody AllKzsx extensionAttribute) {
        int add = extensionAttributeService.add(extensionAttribute);
        if (add==0)
            return ResponseUtil.fail(1,"添加失败，请重试，不行联系管理员");
        extensionAttribute.setKzsxbm(add);
        return ResponseUtil.ok(extensionAttribute);
    }
}
