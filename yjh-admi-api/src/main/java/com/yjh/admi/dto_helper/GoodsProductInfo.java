package com.yjh.admi.dto_helper;
/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
public class GoodsProductInfo {
    private int productId;
    private double price;
    private double costPrice;
    private int number;
    private String url;
    private String value;
    private double biggestDeductionAmount;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getBiggestDeductionAmount() {
        return biggestDeductionAmount;
    }

    public void setBiggestDeductionAmount(double biggestDeductionAmount) {
        this.biggestDeductionAmount = biggestDeductionAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GoodsProductInfo{" +
                "productId=" + productId +
                ", price=" + price +
                ", costPrice=" + costPrice +
                ", number=" + number +
                ", url='" + url + '\'' +
                ", value='" + value + '\'' +
                ", biggestDeductionAmount=" + biggestDeductionAmount +
                '}';
    }
}
