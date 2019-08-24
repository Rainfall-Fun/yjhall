package com.yjh.db.service;

import com.yjh.db.dao.AllSpjggtpMapper;
import com.yjh.db.domain.AllSpjggtp;
import com.yjh.db.domain.AllSpjggtpExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class GoodsAndSpecificationPictureService {
    @Autowired
    AllSpjggtpMapper specificationPictureMapper;

    /**
     * 添加商品及规格图片
     * @param specificationPicture
     * @return
     */
    public boolean addSpecificationPicture(AllSpjggtp specificationPicture){
        try {
            specificationPictureMapper.insertSelective(specificationPicture);
            specificationPicture.getTpxh();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据商品id删除商品规格及图片
     * @param goodsId
     * @return
     */
    public boolean deleteSpecificationPictureByGoodsId(Integer goodsId) {
        try{
            AllSpjggtpExample specificationPictureExample=new AllSpjggtpExample();
            AllSpjggtpExample.Criteria criteria = specificationPictureExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            specificationPictureMapper.deleteByExample(specificationPictureExample);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据商品id的到所有的规格图片服务器地址
     * @param goodsId
     * @return
     */
    public List<String> getAllPicturebyGoodsId(Integer goodsId){
        AllSpjggtpExample specificationPictureExample=new AllSpjggtpExample();
        AllSpjggtpExample.Criteria criteria = specificationPictureExample.createCriteria();
        criteria.andSpbmEqualTo(goodsId);
        List<AllSpjggtp> specificationPictures = specificationPictureMapper.selectByExample(specificationPictureExample);
        List<String> pictures=new ArrayList<>();
        for (AllSpjggtp sp :
                specificationPictures) {
            pictures.add(sp.getTpljjmc());
        }
        return pictures;
    }

    /**
     * 得到所有的商品规格属性图片根据规格属性id
     * @param specificationAttributeId
     * @return
     */
    public List<AllSpjggtp> getSpecificationPicturesBySpeciAttributeId(Integer specificationAttributeId){
        try {
            AllSpjggtpExample specificationPictureExample=new AllSpjggtpExample();
            AllSpjggtpExample.Criteria criteria = specificationPictureExample.createCriteria();
            criteria.andSpggsxzbmEqualTo(specificationAttributeId);
            return specificationPictureMapper.selectByExample(specificationPictureExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 得到商品的封面图
     * @param goodsId
     * @return
     */
    public String getGoodsPicture(int goodsId){
        try {
            AllSpjggtpExample specificationPictureExample=new AllSpjggtpExample();
            AllSpjggtpExample.Criteria criteria = specificationPictureExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            criteria.andSpggsxzbmIsNull();
            List<AllSpjggtp> allSpjggtps = specificationPictureMapper.selectByExample(specificationPictureExample);
            if(allSpjggtps.size()==0)
                return "默认图片";
            return allSpjggtps.get(0).getTpljjmc();
        }catch (Exception e){
            e.printStackTrace();
            return "默认图片";
        }
    }

    /**
     * 得到所有的商品图片
     * @param goodsId
     * @return
     */
    public String[] getAllGoodsPictures(int goodsId){
        String goodsPictures[];
        try {
            AllSpjggtpExample specificationPictureExample=new AllSpjggtpExample();
            AllSpjggtpExample.Criteria criteria = specificationPictureExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            criteria.andSpggsxzbmIsNull();
            List<AllSpjggtp> allSpjggtps = specificationPictureMapper.selectByExample(specificationPictureExample);
            goodsPictures=new String[allSpjggtps.size()];
            for (int i = 0; i < allSpjggtps.size(); i++) {
                goodsPictures[i]=allSpjggtps.get(i).getTpljjmc();
            }
            return goodsPictures;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除所有商品
     * @param goodsId
     * @return
     */
    public boolean deleteGoodsPicture(int goodsId){
        try {
            AllSpjggtpExample specificationPictureExample=new AllSpjggtpExample();
            AllSpjggtpExample.Criteria criteria = specificationPictureExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            specificationPictureMapper.deleteByExample(specificationPictureExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
