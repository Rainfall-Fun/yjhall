package com.yjh.admi.vo;

import com.yjh.db.domain.AllSpfl;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryVo {
    private Integer id;
    private String name;
    //    private String keywords;
    private String desc;
    private String available;
    //    private String iconUrl;
    private String picUrl;
    private LocalDateTime addTime;
    private String addPerson;
    private LocalDateTime lastEditTime;
    private String lastEditPerson;
    private String level;
    private String block;//商品所属板块
    private String pid;//父分类id
    private String[] selectedOptions;
    private List<CategoryVo> children;//商品子分类的vo对象

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public String getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(String addPerson) {
        this.addPerson = addPerson;
    }

    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(LocalDateTime lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getLastEditPerson() {
        return lastEditPerson;
    }

    public void setLastEditPerson(String lastEditPerson) {
        this.lastEditPerson = lastEditPerson;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String[] getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(String[] selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public List<CategoryVo> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryVo> children) {
        this.children = children;
    }

    /**
     * 把商品分类的实体属性传给VO对象属性
     *
     * @param allSpfl 商品分类的对象
     */
    public void AllspflConvertToCategoryVo(AllSpfl allSpfl, String pid) {
        this.id = allSpfl.getSpflbm();
        this.name = allSpfl.getSpfl();
        this.desc = allSpfl.getBz();
        this.available = allSpfl.getSfsx()+"";
        this.picUrl = allSpfl.getSpfltpwj();
        this.addTime = allSpfl.getTjsj();
        this.addPerson = allSpfl.getTjr();
        this.lastEditTime = allSpfl.getZhxgsj();
        this.lastEditPerson = allSpfl.getZhxgr();
        this.level = allSpfl.getFldj().toString();
        this.pid = pid;
    }

}
