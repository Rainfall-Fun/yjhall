package com.yjh.admi.dto;

import com.yjh.db.domain.AllSpfl;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class CategoryRecevicer {
    private Integer available;
    private String[] blockOptions;
    private String desc;
    private String name;
    private String picUrl;
    private String[] selectedOptions;
    private Integer id;

    public AllSpfl convertToAllSpfl(AllSpfl allSpfl, HttpSession session, String operationMethod) {

        allSpfl.setBz(this.desc);//备注
        allSpfl.setSfsx(this.available);//是否失效
        allSpfl.setSpfl(this.name);//分类名
        allSpfl.setSpfltpwj(picUrl);//图片链接

        int code;
        try {
            code = Integer.parseInt(selectedOptions[selectedOptions.length - 1]);
        } catch (Exception e) {
            e.printStackTrace();
            code = 0;
        }
        allSpfl.setSjspflbm(code);//上级分类编码

        LocalDateTime time = LocalDateTime.now();
        String operationPerson;

        try {
            operationPerson = (String) session.getAttribute("userName");
            if (operationPerson.isEmpty()) {
                operationPerson = "无";
            }
        } catch (Exception e) {
            e.printStackTrace();
            operationPerson = "无";
        }

        allSpfl.setZhxgsj(time);//最后修改时间
        allSpfl.setZhxgr(operationPerson);//最后修改人

        if (operationMethod.equals("create")) {
            allSpfl.setTjsj(time);//添加时间
            allSpfl.setTjr(operationPerson);//添加人
        }

        try {
            allSpfl.setSpflbm(this.id);
        } catch (Exception e) {
            e.printStackTrace();
            allSpfl.setSpflbm(null);
        }

        try {
            if (allSpfl.getSjspflbm() == 0) {
                allSpfl.setFldj(1);
            } else {
                allSpfl.setFldj(selectedOptions.length + 1);//分类等级
            }
        } catch (Exception e) {
            e.printStackTrace();
            allSpfl.setFldj(1);
        }

        try {
            allSpfl.setSsbkbm(Integer.parseInt(blockOptions[0]));//所属板块
        } catch (Exception e) {
            e.printStackTrace();
            allSpfl.setSsbkbm(1);
        }

        return allSpfl;
    }

    public CategoryRecevicer(Integer available, String[] blockOptions, String desc, String name, String picUrl, String[] selectedOptions, Integer id) {
        this.available = available;
        this.blockOptions = blockOptions;
        this.desc = desc;
        this.name = name;
        this.picUrl = picUrl;
        this.selectedOptions = selectedOptions;
        this.id = id;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String[] getBlockOptions() {
        return blockOptions;
    }

    public void setBlockOptions(String[] blockOptions) {
        this.blockOptions = blockOptions;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String[] getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(String[] selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public CategoryRecevicer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
