package com.yjh.admi.vo;

import java.util.List;

public class RegionVo {
    private Integer id;
    private String name;
    private int type;
    private String code;

    private List<RegionVo> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<RegionVo> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setChildren(List<RegionVo> children) {
        this.children = children;
    }
}
