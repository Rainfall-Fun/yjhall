package com.yjh.admi.dto_helper;
/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
public class GoodsSpecificationInfo {
    private int specificationId;//规格id
    private String specification;//规格名
    private String value;//规格值
    private String picUrl;//规格图片

    public int getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(int specificationId) {
        this.specificationId = specificationId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "GoodsSpecificationInfo{" +
                "specificationId=" + specificationId +
                ", specification='" + specification + '\'' +
                ", value='" + value + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
