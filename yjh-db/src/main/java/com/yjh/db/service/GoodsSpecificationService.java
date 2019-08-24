package com.yjh.db.service;

import com.yjh.db.dao.AllSpggMapper;
import com.yjh.db.domain.AllSpgg;
import com.yjh.db.domain.AllSpggExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class GoodsSpecificationService {
    @Autowired
    AllSpggMapper goodsSpecificationMapper;
    public int addGoodsSpecification(AllSpgg goodsSpecification){
        try{
            goodsSpecificationMapper.insertSelective(goodsSpecification);
            return goodsSpecification.getSpggbm();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 根据商品id得到商品规格
     * @param goodsId
     * @return
     */
    public List<AllSpgg> getGoodsSpecificationsByGoodsId(Integer goodsId){
        try{
            AllSpggExample goodsSpeExample=new AllSpggExample();
            AllSpggExample.Criteria criteria = goodsSpeExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            return goodsSpecificationMapper.selectByExample(goodsSpeExample);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据商品id删除所有的商品规格
     * @param goodsId
     * @return
     */
    public boolean deleteSpecificationByGoodsId(Integer goodsId){
        try {
            AllSpggExample goodsSpeExample=new AllSpggExample();
            AllSpggExample.Criteria criteria = goodsSpeExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            goodsSpecificationMapper.deleteByExample(goodsSpeExample);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateSpecificationInfo(AllSpgg specification){
        try{
            goodsSpecificationMapper.updateByPrimaryKeySelective(specification);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}
