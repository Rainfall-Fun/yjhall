package com.yjh.wx.web;

import com.yjh.core.util.ResponseUtil;
import com.yjh.db.domain.AllSpfl;
import com.yjh.db.service.GoodsCategoryService;
import com.yjh.wx.service.HomeCacheManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类目服务
 */
@RestController
@RequestMapping("/wx/catalog")
@Validated
public class WxCatalogController {
    private final Log logger = LogFactory.getLog(WxCatalogController.class);

    @Autowired
    private GoodsCategoryService categoryService;

    /**
     * 分类详情
     *
     * @param id   分类类目ID。
     *             如果分类类目ID是空，则选择第一个分类类目。
     *             需要注意，这里分类类目是一级类目
     * @return 分类详情
     */
    @GetMapping("index")
    public Object index(Integer id) {

        // 所有一级分类目录
        List<AllSpfl> l1CatList = categoryService.getGoodsL1Categories();

        // 当前一级分类目录
        AllSpfl currentCategory = null;
        if (id != null) {
            currentCategory = categoryService.getGoodsCategoryById(id);
        } else {
            currentCategory = l1CatList.get(0);
        }

        // 当前一级分类目录对应的二级分类目录
        List<AllSpfl> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.getGoodsL2CategoriesBySuperiorCategoryId(currentCategory.getSpflbm());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("categoryList", l1CatList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return ResponseUtil.ok(data);
    }

    /**
     * 所有分类数据
     *
     * @return 所有分类数据
     */
    @GetMapping("all")
    public Object queryAll() {
        //优先从缓存中读取
        if (HomeCacheManager.hasData(HomeCacheManager.CATALOG)) {
            return ResponseUtil.ok(HomeCacheManager.getCacheData(HomeCacheManager.CATALOG));
        }


        // 所有一级分类目录
        List<AllSpfl> l1CatList = categoryService.getGoodsL1Categories();

        //所有子分类列表
        Map<Integer, List<AllSpfl>> allList = new HashMap<>();
        List<AllSpfl> sub;
        for (AllSpfl category : l1CatList) {
            sub = categoryService.getGoodsL2CategoriesBySuperiorCategoryId(category.getSpflbm());
            allList.put(category.getSpflbm(), sub);
        }

        // 当前一级分类目录
        AllSpfl currentCategory = l1CatList.get(0);

        // 当前一级分类目录对应的二级分类目录
        List<AllSpfl> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.getGoodsL2CategoriesBySuperiorCategoryId(currentCategory.getSpflbm());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("categoryList", l1CatList);
        data.put("allList", allList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);

        //缓存数据
        HomeCacheManager.loadData(HomeCacheManager.CATALOG, data);
        return ResponseUtil.ok(data);
    }

    /**
     * 当前分类栏目
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     */
    @GetMapping("current")
    public Object current(@NotNull Integer id) {
        // 当前分类
        AllSpfl currentCategory = categoryService.getGoodsCategoryById(id);
        if(currentCategory == null){
            return ResponseUtil.badArgumentValue();
        }
        List<AllSpfl> currentSubCategory = categoryService.getGoodsL2CategoriesBySuperiorCategoryId(currentCategory.getSpflbm());

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return ResponseUtil.ok(data);
    }
}