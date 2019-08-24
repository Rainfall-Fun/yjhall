package com.yjh.db.service;

import com.yjh.db.dao.AllSpjkctpMapper;
import com.yjh.db.domain.AllSpjggtp;
import com.yjh.db.domain.AllSpjggtpExample;
import com.yjh.db.domain.AllSpjkctp;
import com.yjh.db.domain.AllSpjkctpExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/7 16:15
 */
@Service
public class GoodsAndProductPictureService {
    @Autowired
    AllSpjkctpMapper goodsAndProductPicMapper;

    /**
     * 添加商品及库存图片到数据库
     * @param productPicture
     * @return
     */
    public boolean add(AllSpjkctp productPicture){
        try {
            int i = goodsAndProductPicMapper.insertSelective(productPicture);
            return i==1?true:false;

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
            AllSpjkctpExample productPictureExample=new AllSpjkctpExample();
            AllSpjkctpExample.Criteria criteria = productPictureExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            goodsAndProductPicMapper.deleteByExample(productPictureExample);
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
        AllSpjkctpExample specificationPictureExample=new AllSpjkctpExample();
        AllSpjkctpExample.Criteria criteria = specificationPictureExample.createCriteria();
        criteria.andSpbmEqualTo(goodsId);
        List<AllSpjkctp> specificationPictures = goodsAndProductPicMapper.selectByExample(specificationPictureExample);
        List<String> pictures=new ArrayList<>();
        for (AllSpjkctp sp :
                specificationPictures) {
            pictures.add(sp.getTpljjmc());
        }
        return pictures;
    }

    /**
     * 得到商品的封面图
     * @param goodsId
     * @return
     */
    public String getGoodsPicture(int goodsId){
        try {
            AllSpjkctpExample specificationPictureExample=new AllSpjkctpExample();
            AllSpjkctpExample.Criteria criteria = specificationPictureExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            criteria.andSpkcbmIsNull();
            List<AllSpjkctp> allSpjggtps = goodsAndProductPicMapper.selectByExample(specificationPictureExample);
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
            AllSpjkctpExample specificationPictureExample=new AllSpjkctpExample();
            AllSpjkctpExample.Criteria criteria = specificationPictureExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            criteria.andSpkcbmIsNull();
            List<AllSpjkctp> allSpjggtps = goodsAndProductPicMapper.selectByExample(specificationPictureExample);
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
}
