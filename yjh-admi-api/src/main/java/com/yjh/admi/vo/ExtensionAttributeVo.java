package com.yjh.admi.vo;

/**
 * Created by 彭椿悦 on 2019/8/4 15:19
 */
public class ExtensionAttributeVo {
    private Integer extensionAttributeId;
    private String extensionAttributeName;
    private String extensionAttributeValue;

    public Integer getExtensionAttributeId() {
        return extensionAttributeId;
    }

    public void setExtensionAttributeId(Integer extensionAttributeId) {
        this.extensionAttributeId = extensionAttributeId;
    }

    public String getExtensionAttributeName() {
        return extensionAttributeName;
    }

    public void setExtensionAttributeName(String extensionAttributeName) {
        this.extensionAttributeName = extensionAttributeName;
    }

    public String getExtensionAttributeValue() {
        return extensionAttributeValue;
    }

    public void setExtensionAttributeValue(String extensionAttributeValue) {
        this.extensionAttributeValue = extensionAttributeValue;
    }

    @Override
    public String toString() {
        return "ExtensionAttributeVo{" +
                "extensionAttributeId=" + extensionAttributeId +
                ", extensionAttributeName='" + extensionAttributeName + '\'' +
                ", extensionAttributeValue='" + extensionAttributeValue + '\'' +
                '}';
    }
}
