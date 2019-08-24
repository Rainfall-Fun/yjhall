package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.AllSpppMapper;
import com.yjh.db.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class GoodsBrandService {
    @Autowired
    AllSpppMapper goodsBrandMapper;
    public List<AllSppp> getAllSppp(){
        AllSpppExample goodsBrandExample=new AllSpppExample();
        return goodsBrandMapper.selectByExample(goodsBrandExample);
    }

    /**
     * 根据条件查出商品品牌
     * @param goodsSn
     * @param name
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<AllSppp> querySelective(String goodsSn, String name, Integer page, Integer size, String sort, String order) {
        AllSpppExample goodsBrandExample=new AllSpppExample();
        AllSpppExample.Criteria criteria = goodsBrandExample.createCriteria();

        if (!StringUtils.isEmpty(goodsSn)) {
            criteria.andSpppbmEqualTo(new Integer(goodsSn));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andPpmcLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            goodsBrandExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return goodsBrandMapper.selectByExample(goodsBrandExample);
    }

    /**
     * 删除品牌信息，根据品牌id
     * @param brandId
     * @return
     */
    public int deleteBrand(Integer brandId){
        try {
            goodsBrandMapper.deleteByPrimaryKey(brandId);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 修改商品品牌信息
     * @param brand
     * @return //返回1，更新成功
     */
    public int updateBrandInfo(AllSppp brand){
        try {
            return goodsBrandMapper.updateByPrimaryKeySelective(brand);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 添加商品品牌信息
     * @param brand
     * @return
     */
    public int addBrandInfo(AllSppp brand){
        try {
            goodsBrandMapper.insertSelective(brand);
            return brand.getSpppbm();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 判断品牌名是否存在
     * @param brandName
     * @return 存在返回true
     */
    public boolean checkBrandName(String brandName){
        AllSpppExample goodsBrandExample=new AllSpppExample();
        AllSpppExample.Criteria criteria = goodsBrandExample.createCriteria();
        criteria.andPpmcEqualTo(brandName);
        List<AllSppp> allSppps = goodsBrandMapper.selectByExample(goodsBrandExample);
        if(allSppps.size()!=0)
            return true;
        else
            return false;
    }

    /**
     * 根据商品品牌id得到商品品牌实体
     * @param id
     * @return
     */
    public AllSppp getBrandById(int id){
        try {
            return goodsBrandMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
