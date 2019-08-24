package com.yjh.admi.dto_helper;

import com.yjh.db.domain.AllSpjbxx;

/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
public class GoodsInfo {
    private int goodsId;//商品id
    private String name;//商品名称
    private double counterPrice;//市场价格
    private double retailPrice;//当前价格
    private double costPrice;//成本价
    private double taxCost;//税费
    private int isRecommendation;//是否是推荐商品吗，1：是
    private int isOnSale;//是否上架，1：是
    private String picUrl;//商品封面图地址
    private String unit;//商品计量单位
    private int brandId;//所属品牌id
    private String supplierId;//供应商id
    private int belongModule;
    private String brief;//简短介绍
    private String detail;//详细介绍
    private String categoryId;//商品分类
    private int paymentWay;//支付方式
    private int freightRule;//运费规则
    private String[] gallery;

    public String[] getGallery() {
        return gallery;
    }

    public void setGallery(String[] gallery) {
        this.gallery = gallery;
    }

    public int getFreightRule() {
        return freightRule;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public void setFreightRule(int freightRule) {
        this.freightRule = freightRule;
    }

    public int getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(int paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCounterPrice() {
        return counterPrice;
    }

    public void setCounterPrice(double counterPrice) {
        this.counterPrice = counterPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getTaxCost() {
        return taxCost;
    }

    public void setTaxCost(double taxCost) {
        this.taxCost = taxCost;
    }

    public int getIsRecommendation() {
        return isRecommendation;
    }

    public void setIsRecommendation(int isRecommendation) {
        this.isRecommendation = isRecommendation;
    }

    public int getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(int isOnSale) {
        this.isOnSale = isOnSale;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }



    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public int getBelongModule() {
        return belongModule;
    }

    public void setBelongModule(int belongModule) {
        this.belongModule = belongModule;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "goodsId=" + goodsId +
                ", name='" + name + '\'' +
                ", counterPrice=" + counterPrice +
                ", retailPrice=" + retailPrice +
                ", costPrice=" + costPrice +
                ", taxCost=" + taxCost +
                ", isRecommendation=" + isRecommendation +
                ", isOnSale=" + isOnSale +
                ", picUrl='" + picUrl + '\'' +
                ", unit='" + unit + '\'' +
                ", brandId=" + brandId +
                ", supplierId='" + supplierId + '\'' +
                ", belongModule=" + belongModule +
                ", brief='" + brief + '\'' +
                ", detail='" + detail + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", paymentWay=" + paymentWay +
                ", freightRule=" + freightRule +
                '}';
    }
}
