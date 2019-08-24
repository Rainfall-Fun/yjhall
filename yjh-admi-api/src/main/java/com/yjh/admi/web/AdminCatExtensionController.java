package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.admi.dto.AddCatExtension;
import com.yjh.admi.dto.CategoryExtension;
import com.yjh.admi.vo.ExtensionAttributeVo;
import com.yjh.core.util.ResponseUtil;
import com.yjh.db.domain.AllKzsx;
import com.yjh.db.domain.AllSplbkzsxKey;
import com.yjh.db.service.ExtensionAttributeService;
import com.yjh.db.service.GoodsCategoryExtensionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/4 20:42
 */
@RestController
@RequestMapping("/admin/categoryExtension")
@Validated
public class AdminCatExtensionController {

    @Autowired
    GoodsCategoryExtensionService goodsCategoryExtensionService;
    @Autowired
    ExtensionAttributeService extensionAttributeService;

    @RequiresPermissions("admin:categoryExtension:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "类别扩展属性"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody CategoryExtension categoryExtension) {
        String categoryId = categoryExtension.getCategoryId();
        Integer extensionAttributeId = categoryExtension.getExtensionAttributeVo().getExtensionAttributeId();
        if(StringUtils.isEmpty(categoryId)||StringUtils.isEmpty(extensionAttributeId))
            return ResponseUtil.badArgument();
        boolean delete = goodsCategoryExtensionService.delete(Integer.parseInt(categoryId), extensionAttributeId);
        return delete?ResponseUtil.ok():ResponseUtil.fail(1,"删除失败，请重试，不行联系管理员");
    }

    /**
     * 得到商品的属性值
     * @return
     */
    @GetMapping("/getGoodsAttribute")
    public Object getGoodsAttribute(int categoryId){
        List<Integer> extensionAttributeIds = goodsCategoryExtensionService.getAllExtensionAttributeByCategoryId(categoryId);
        List<AllKzsx> allExtensionAttributes = extensionAttributeService.getAllExtensionAttributeBy(extensionAttributeIds);
        if(allExtensionAttributes==null)
            return ResponseUtil.ok(new ArrayList<>());
        List<ExtensionAttributeVo> extensionAttributeVos=new ArrayList<>(allExtensionAttributes.size());
        for (AllKzsx allKzsx:
                allExtensionAttributes) {
            ExtensionAttributeVo extensionAttributeVo=new ExtensionAttributeVo();
            extensionAttributeVo.setExtensionAttributeId(allKzsx.getKzsxbm());
            extensionAttributeVo.setExtensionAttributeName(allKzsx.getSxmc());
            extensionAttributeVos.add(extensionAttributeVo);
        }
        return ResponseUtil.ok(extensionAttributeVos);
    }

    @GetMapping("/getAllExtensionAttributes")
    public Object getAllExtensionAttributes(){
        List<AllKzsx> aLlExtensionAttribute = extensionAttributeService.getALlExtensionAttribute();
        return ResponseUtil.ok(aLlExtensionAttribute);
    }
    @RequiresPermissions("admin:categoryExtension:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "类别扩展属性"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody AddCatExtension addCatExtension){
        int categoryId = addCatExtension.getCategoryId();
        int[] value = addCatExtension.getValue();
        if(StringUtils.isEmpty(categoryId)||categoryId==0)
            return ResponseUtil.fail(1,"请选择商品分类");
        if (value.length==0)
            return ResponseUtil.fail(1,"请为此分类添加扩展属性");
        goodsCategoryExtensionService.delete(categoryId);
        for (int i = 0; i < value.length; i++) {
            AllSplbkzsxKey catExtensionAttribute=new AllSplbkzsxKey();
            catExtensionAttribute.setKzsxbm(value[i]);
            catExtensionAttribute.setSpflbm(categoryId);
            goodsCategoryExtensionService.add(catExtensionAttribute);
        }
        Object list = getGoodsAttribute(categoryId);
        return ResponseUtil.ok(list);
    }
}
