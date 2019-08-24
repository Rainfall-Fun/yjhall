package com.yjh.wx.dto;

import java.math.BigDecimal;

/**
 * Created by 彭椿悦 on 2019/8/9 22:25
 */
public class PurchaseInfo {
    private Integer goodsId;
    private Integer productId;
    private Short number;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PurchaseInfo{" +
                "goodsId=" + goodsId +
                ", productId=" + productId +
                ", number=" + number +
                '}';
    }
}
