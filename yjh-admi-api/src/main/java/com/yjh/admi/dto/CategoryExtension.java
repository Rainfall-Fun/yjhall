package com.yjh.admi.dto;

import com.yjh.admi.vo.ExtensionAttributeVo;

/**
 * Created by 彭椿悦 on 2019/8/4 22:45
 */
public class CategoryExtension {
    ExtensionAttributeVo extensionAttributeVo;
    String categoryId;

    public ExtensionAttributeVo getExtensionAttributeVo() {
        return extensionAttributeVo;
    }

    public void setExtensionAttributeVo(ExtensionAttributeVo extensionAttributeVo) {
        this.extensionAttributeVo = extensionAttributeVo;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
