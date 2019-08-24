package com.yjh.wx.web;

import com.yjh.core.util.ResponseUtil;
import com.yjh.db.domain.AllSpjbxx;
import com.yjh.db.domain.AllSpkc;
import com.yjh.db.service.GoodsAndProductPictureService;
import com.yjh.db.service.GoodsProductService;
import com.yjh.db.service.GoodsService;
import com.yjh.wx.annotation.LoginUser;
import com.yjh.wx.dto.GoodsInfo;
import com.yjh.wx.dto.PurchaseInfo;
import com.yjh.wx.service.WxCartController;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.yjh.wx.util.WxResponseCode.GOODS_NO_STOCK;
import static com.yjh.wx.util.WxResponseCode.GOODS_UNSHELVE;

/**
 * Created by 彭椿悦 on 2019/8/9 22:19
 */
@RestController
@RequestMapping("/wx/check")
@Validated
public class WxPurchaseController {
    private final Log logger = LogFactory.getLog(WxPurchaseController.class);
    @Autowired
    GoodsProductService goodsProductService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsAndProductPictureService goodsAndProductPictureService;

    @PostMapping("checkProducts")
    public Object fastadd(@LoginUser Integer userId, @RequestBody PurchaseInfo purchaseInfo) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (purchaseInfo == null) {
            return ResponseUtil.badArgument();
        }
        Integer productId = purchaseInfo.getProductId();
        Integer number = purchaseInfo.getNumber().intValue();
        Integer goodsId = purchaseInfo.getGoodsId();
        if (!ObjectUtils.allNotNull(productId, number, goodsId)) {
            return ResponseUtil.badArgument();
        }
        if(number <= 0){
            return ResponseUtil.badArgument();
        }

        //判断商品是否下架
        AllSpjbxx goods = goodsService.getGoodsByGoodsId(goodsId);
        if (goods == null || !(goods.getSfsj()==1?true:false)) {
            return ResponseUtil.fail(GOODS_UNSHELVE, "商品已下架");
        }
        //判断是库存是否充足
        AllSpkc goodsProducts = goodsProductService.getGoodsProducts(productId);
        if(goodsProducts.getKc()<number)
            return ResponseUtil.fail(GOODS_NO_STOCK,"商品库存不足");
//        Map<String, Object> data = new HashMap<>();
//        GoodsInfo goodsInfo=new GoodsInfo();
//        goodsInfo.setGoodsId(goods.getSpbm());
//        goodsInfo.setName(goods.getSpmc());
//        goodsInfo.setPicUrl(goodsAndProductPictureService.getGoodsPicture(goodsId));
//        goodsInfo.setRetailPrice(goodsProducts.getSpsj().doubleValue());
//        data.put("goodsInfo",goodsInfo);
//        data.put("productsInfo",goodsProducts);
        return ResponseUtil.ok();
    }
}
