package com.yjh.admi.dto;


import com.yjh.admi.dto_helper.GoodsInfo;
import com.yjh.admi.dto_helper.GoodsProductInfo;
import com.yjh.admi.dto_helper.GoodsSpecificationInfo;
import com.yjh.admi.vo.ExtensionAttributeVo;

/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
public class GoodsAllinone {
    GoodsInfo goods;
    GoodsSpecificationInfo[] specifications;
    ExtensionAttributeVo[] extensionAttributes;
    GoodsProductInfo[] products;

    public GoodsInfo getGoods() {
        return goods;
    }

    public void setGoods(GoodsInfo goods) {
        this.goods = goods;
    }

    public GoodsProductInfo[] getProducts() {
        return products;
    }

    public void setProducts(GoodsProductInfo[] products) {
        this.products = products;
    }

    public GoodsSpecificationInfo[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(GoodsSpecificationInfo[] specifications) {
        this.specifications = specifications;
    }


    public ExtensionAttributeVo[] getExtensionAttributes() {
        return extensionAttributes;
    }

    public void setExtensionAttributes(ExtensionAttributeVo[] extensionAttributes) {
        this.extensionAttributes = extensionAttributes;
    }
}
