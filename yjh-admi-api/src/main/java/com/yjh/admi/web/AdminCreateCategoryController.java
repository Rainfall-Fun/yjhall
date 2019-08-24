package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.admi.dto.CategoryRecevicer;
import com.yjh.admi.service.BlockService;
import com.yjh.admi.vo.CategoryVo;
import com.yjh.core.util.ResponseUtil;
import com.yjh.db.domain.AllSpfl;
import com.yjh.db.domain.AllSpssbk;
import com.yjh.db.service.GoodsCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/category")
public class AdminCreateCategoryController {

    @Autowired
    GoodsCategoryService goodsCategoryService;

    @Autowired
    BlockService blockService;

    private Object validate(AllSpfl allSpfl) {

        if (StringUtils.isEmpty(allSpfl.getSpfl())) {
            return ResponseUtil.badArgument();
        }

        Integer level = allSpfl.getFldj();
        if (StringUtils.isEmpty(level) || level == 0) {
            return ResponseUtil.badArgument();
        }

        if (StringUtils.isEmpty(allSpfl.getSsbkbm())) {
            return ResponseUtil.badArgument();
        }

        if (StringUtils.isEmpty(allSpfl.getSjspflbm())) {
            return ResponseUtil.badArgument();
        }

        if (StringUtils.isEmpty(allSpfl.getSfsx())) {
            return ResponseUtil.badArgument();
        }

        if (StringUtils.isEmpty(allSpfl.getTjsj())) {
            return ResponseUtil.badArgument();
        }

        if (StringUtils.isEmpty(allSpfl.getTjr())) {
            return ResponseUtil.badArgument();
        }

        if (StringUtils.isEmpty(allSpfl.getZhxgsj())) {
            return ResponseUtil.badArgument();
        }

        if (StringUtils.isEmpty(allSpfl.getZhxgr())) {
            return ResponseUtil.badArgument();
        }


        return null;
    }

    @RequiresPermissions("admin:category:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "商品类目"}, button = "查询")
    @GetMapping("/list")
    public Object list() {

        // 一级分类
        List<CategoryVo> categoryVos1 = new ArrayList<>();
        List<AllSpfl> L1Category = goodsCategoryService.findByCategoryLevel(1);
        for (AllSpfl allSpfl1 : L1Category) {
            CategoryVo categoryVo1 = new CategoryVo();
            categoryVo1.AllspflConvertToCategoryVo(allSpfl1, null);
            categoryVo1.setBlock(blockService.getblockName(allSpfl1.getSsbkbm()));
            categoryVo1.setSelectedOptions(goodsCategoryService.getCategoryTree(allSpfl1.getSpflbm()));

            // 二级分类
            List<CategoryVo> categoryVos2 = new ArrayList<>();
            List<AllSpfl> L2Category = goodsCategoryService.findByCategorySuperiorId(allSpfl1.getSpflbm());
            for (AllSpfl allSpfl2 : L2Category) {
                CategoryVo categoryVo2 = new CategoryVo();
                categoryVo2.AllspflConvertToCategoryVo(allSpfl2, goodsCategoryService.findSuperiorNameBySuperiorId(allSpfl2.getSjspflbm()));
                categoryVo2.setBlock(blockService.findByBlockId(allSpfl2.getSsbkbm()));
                categoryVo2.setSelectedOptions(goodsCategoryService.getCategoryTree(allSpfl2.getSpflbm()));

                // 三级分类
                List<CategoryVo> categoryVos3 = new ArrayList<>();
                List<AllSpfl> L3Category = goodsCategoryService.findByCategorySuperiorId(allSpfl2.getSpflbm());
                for (AllSpfl allSpfl3 : L3Category) {
                    CategoryVo categoryVo3 = new CategoryVo();
                    categoryVo3.AllspflConvertToCategoryVo(allSpfl3, goodsCategoryService.findSuperiorNameBySuperiorId(allSpfl3.getSjspflbm()));
                    categoryVo3.setBlock(blockService.findByBlockId(allSpfl3.getSsbkbm()));
                    categoryVo3.setSelectedOptions(goodsCategoryService.getCategoryTree(allSpfl3.getSpflbm()));

                    // 四级分类
                    List<CategoryVo> categoryVos4 = new ArrayList<>();
                    List<AllSpfl> L4Category = goodsCategoryService.findByCategorySuperiorId(allSpfl3.getSpflbm());
                    for (AllSpfl allSpfl4 : L4Category) {
                        CategoryVo categoryVo4 = new CategoryVo();
                        categoryVo4.AllspflConvertToCategoryVo(allSpfl4, goodsCategoryService.findSuperiorNameBySuperiorId(allSpfl4.getSjspflbm()));
                        categoryVo4.setBlock(blockService.findByBlockId(allSpfl4.getSsbkbm()));
                        categoryVo4.setSelectedOptions(goodsCategoryService.getCategoryTree(allSpfl4.getSpflbm()));

                        categoryVo4.setChildren(null);
                        categoryVos4.add(categoryVo4);

                    }

                    categoryVo3.setChildren(categoryVos4);
                    categoryVos3.add(categoryVo3);
                }
                categoryVo2.setChildren(categoryVos3);
                categoryVos2.add(categoryVo2);
            }
            categoryVo1.setChildren(categoryVos2);
            categoryVos1.add(categoryVo1);
        }

        return ResponseUtil.okList(categoryVos1);

    }

    @RequiresPermissions("admin:category:create")
    @RequiresPermissionsDesc(menu = {"商场管理", "商品类目"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody CategoryRecevicer categoryRecevicer, HttpServletRequest request) {

        HttpSession session = request.getSession();

        AllSpfl tmp = new AllSpfl();
        AllSpfl allSpfl = categoryRecevicer.convertToAllSpfl(tmp, session, "create");

        if (goodsCategoryService.isUnderTreeRepeatedById(allSpfl.getSjspflbm(), allSpfl.getSpfl())) {
            return ResponseUtil.fail(401, "参数不对");
        }

        Object error = validate(allSpfl);
        if (error != null) {
            return error;
        }
        goodsCategoryService.addGoodsCategory(allSpfl);

        return ResponseUtil.ok(allSpfl);

    }

    @GetMapping("/l1")
    public Object catl1() {
        List<AllSpfl> l1AllSpfls = goodsCategoryService.findByCategoryLevel(1);
        List<Map<String, Object>> data1 = new ArrayList<>(l1AllSpfls.size());

        Map<String, Object> first = new HashMap<>();
        first.put("value", "0");
        first.put("label", "无子分类");

        data1.add(first);

        for (AllSpfl allSpfl1 : l1AllSpfls) {
            Map<String, Object> d = new HashMap<>(2);
            d.put("value", allSpfl1.getSpflbm() + "");
            d.put("label", allSpfl1.getSpfl());

            List<Map<String, Object>> data2 = new ArrayList<>();
            List<AllSpfl> l2AllSpfls = goodsCategoryService.findByCategorySuperiorId(allSpfl1.getSpflbm());
            for (AllSpfl allSpfl2 : l2AllSpfls) {
                Map<String, Object> dd = new HashMap<>();
                dd.put("value", allSpfl2.getSpflbm() + "");
                dd.put("label", allSpfl2.getSpfl());

                List<Map<String, Object>> data3 = new ArrayList<>();
                List<AllSpfl> l3AllSspfls = goodsCategoryService.findByCategorySuperiorId(allSpfl2.getSpflbm());
                for (AllSpfl allSpfl3 : l3AllSspfls) {
                    Map<String, Object> ddd = new HashMap<>();
                    ddd.put("value", allSpfl3.getSpflbm() + "");
                    ddd.put("label", allSpfl3.getSpfl());

                    data3.add(ddd);
                }

                dd.put("children", data3);
                data2.add(dd);
            }

            d.put("children", data2);
            data1.add(d);
        }
        System.out.println("l1l1l1l1ll1l1l1l1l1l1l1l");
        return ResponseUtil.okList(data1);
    }

    @GetMapping("/blockMsg")
    public Object blockMsg() {
        List<AllSpssbk> allSpssbks = blockService.findAllBlock();
        List<Map<String, String>> mapList = new ArrayList<>();

        for (AllSpssbk allSpssbk : allSpssbks) {
            Map<String, String> map = new HashMap<>();
            map.put("label", allSpssbk.getSsbk());
            map.put("value", allSpssbk.getSsbkbm() + "");
            mapList.add(map);
        }

        return ResponseUtil.okList(mapList);
    }

    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        AllSpfl allSpfl = goodsCategoryService.findById(id);
        return ResponseUtil.ok(allSpfl);
    }

    @RequiresPermissions("admin:category:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "商品类目"}, button = "修改")
    @PostMapping("/update")
    public Object update(@RequestBody CategoryRecevicer categoryRecevicer, HttpServletRequest request) {

        HttpSession session = request.getSession();

        AllSpfl tmp = goodsCategoryService.findById(categoryRecevicer.getId());

        AllSpfl allSpfl = categoryRecevicer.convertToAllSpfl(tmp, session, "update");


        if (!goodsCategoryService.findById(allSpfl.getSpflbm()).getSpfl().equals(allSpfl.getSpfl())) {

            if (allSpfl.getSjspflbm() == 0) {
                if (goodsCategoryService.isUnderTreeRepeatedById(0, allSpfl.getSpfl())) {
                    return ResponseUtil.fail(402, "参数不对");
                }
            } else {
                if (goodsCategoryService.isUnderTreeRepeatedById(allSpfl.getSjspflbm(), allSpfl.getSpfl())) {
                    return ResponseUtil.fail(402, "参数不对");
                }
            }


        }


        Object error = validate(allSpfl);
        if (error != null) {
            return error;
        }

        if (!goodsCategoryService.updateById(allSpfl)) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok();
    }


    @RequiresPermissions("admin:category:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "商品类目"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody CategoryVo category) {
        Integer id = category.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        if (goodsCategoryService.deleteAllChildrenById(id)) {
            return ResponseUtil.ok();

        } else {
            return ResponseUtil.fail();
        }
    }


}
