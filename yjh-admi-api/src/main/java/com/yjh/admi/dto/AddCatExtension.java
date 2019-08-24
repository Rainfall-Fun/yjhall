package com.yjh.admi.dto;

/**
 * Created by 彭椿悦 on 2019/8/5 11:04
 */
public class AddCatExtension {
    int categoryId;
    int []value;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int[] getValue() {
        return value;
    }

    public void setValue(int[] value) {
        this.value = value;
    }
}
