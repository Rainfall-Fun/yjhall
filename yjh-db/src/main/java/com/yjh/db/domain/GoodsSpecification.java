package com.yjh.db.domain;

import java.math.BigDecimal;

/**
 * Created by 彭椿悦 on 2019/8/6 14:31
 */
public class GoodsSpecification {
    private BigDecimal price;//价格
    private Integer id;//规格id
    private BigDecimal amount;//库存数量
    private String name;//规格名称
    private String value;//规格值
    private String urlPic;//该规格图片

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public String getUrlPic() {
        return urlPic;
    }

    public void setUrlPic(String urlPic) {
        this.urlPic = urlPic;
    }

    @Override
    public String toString() {
        return "GoodsSpecification{" +
                "price=" + price +
                ", id=" + id +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", urlPic='" + urlPic + '\'' +
                '}';
    }
}
