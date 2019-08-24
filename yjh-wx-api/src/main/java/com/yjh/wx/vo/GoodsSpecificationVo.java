package com.yjh.wx.vo;

/**
 * Created by 彭椿悦 on 2019/8/6 9:27
 */
public class GoodsSpecificationVo {
    private double price;//价格
    private int id;//规格id
    private int amount;//库存数量
    private String name;//规格名称
    private String value;//规格值
    private String urlPic;//该规格图片

    public String getUrlPic() {
        return urlPic;
    }

    public void setUrlPic(String urlPic) {
        this.urlPic = urlPic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GoodsSpecificationVo{" +
                "price=" + price +
                ", id=" + id +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
