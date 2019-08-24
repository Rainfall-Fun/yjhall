package com.yjh.db.service;

import com.yjh.db.dao.AllSpggsxzMapper;
import com.yjh.db.dao.GoodsSpecificationMapper;
import com.yjh.db.domain.AllSpggsxz;
import com.yjh.db.domain.AllSpggsxzExample;
import com.yjh.db.domain.GoodsSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/29 12:01
 * 商品规格属性值Service
 */
@Service
public class GoodsSpecificationAttributeService {
    @Autowired
    AllSpggsxzMapper specificationAttributeMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    /**
     * 添加商品规格属性，成功返回添加后记录的主键，不成功则返回0
     * @param specificationAttribute
     * @return
     */
    public int addSpecificationAttribute(AllSpggsxz specificationAttribute){
        try{
            specificationAttributeMapper.insertSelective(specificationAttribute);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return specificationAttribute.getSpggsxzbm();
    }

    /**
     * 根据商品规格id删除所有的商品规格属性值
     * @param specificationId
     * @return
     */
    public boolean deleteSpecificationAttributesBySpecificationId(Integer specificationId){
        try{
            AllSpggsxzExample specificationAttributeExample=new AllSpggsxzExample();
            AllSpggsxzExample.Criteria criteria = specificationAttributeExample.createCriteria();
            criteria.andSpggbmEqualTo(specificationId);
            specificationAttributeMapper.deleteByExample(specificationAttributeExample);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * 根据商品规格编码得到商品规格属性值
     * @param specificationId
     * @return
     */
    public List<AllSpggsxz> getSpecificationAttributesBySpecificationId(Integer specificationId){
        try{
            AllSpggsxzExample specificationAttributeExample=new AllSpggsxzExample();
            AllSpggsxzExample.Criteria criteria = specificationAttributeExample.createCriteria();
            criteria.andSpggbmEqualTo(specificationId);
            return specificationAttributeMapper.selectByExample(specificationAttributeExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改商品规格属性值
     * @param specificationAttribute
     * @return
     */
    public boolean updateSpecificationAttribute(AllSpggsxz specificationAttribute){
        try {
            specificationAttributeMapper.updateByPrimaryKeySelective(specificationAttribute);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 得到商品规格属性值及其规格图片
     * @param goodsId
     * @return
     */
    public List<GoodsSpecification> getSpecificationByGoodsId(int goodsId){
        return goodsSpecificationMapper.getSpecificationByGoodsId(goodsId);
    }
}
