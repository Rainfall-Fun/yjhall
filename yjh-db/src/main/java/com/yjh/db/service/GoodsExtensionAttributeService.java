package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.AllSpkzsxzMapper;
import com.yjh.db.domain.AllSpkzsxz;
import com.yjh.db.domain.AllSpkzsxzExample;
import com.yjh.db.domain.AllSplbkzsxExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/4 16:03
 */
@Service
public class GoodsExtensionAttributeService {
    @Autowired
    AllSpkzsxzMapper extensionAttributeMapper;

    /**
     * 添加商品扩展属性值
     * @param extensionAttribute
     * @return
     */
    public boolean add(AllSpkzsxz extensionAttribute){
        try {
            int i = extensionAttributeMapper.insertSelective(extensionAttribute);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 根据扩展属性id删除商品扩展属性值
     * @param extensionAttributeId
     * @return
     */
    public boolean deleteByExtensionAttributeId(int extensionAttributeId){
        try{
            AllSpkzsxzExample goodsExtensionAttributeExample=new AllSpkzsxzExample();
            AllSpkzsxzExample.Criteria criteria = goodsExtensionAttributeExample.createCriteria();
            criteria.andKzsxbmEqualTo(extensionAttributeId);
            extensionAttributeMapper.deleteByExample(goodsExtensionAttributeExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据商品id得到商品的扩展属性值
     * @param goodsId
     * @return
     */
    public List<AllSpkzsxz> getExtensionAttributeValue(int goodsId){
        try {
            AllSpkzsxzExample goodsExtensionAttributeExample=new AllSpkzsxzExample();
            AllSpkzsxzExample.Criteria criteria = goodsExtensionAttributeExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            return extensionAttributeMapper.selectByExample(goodsExtensionAttributeExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据商品id删除商品扩展属性值
     * @param goodsId
     * @return
     */
    public boolean deleteByGoodsId(int goodsId){
        try {
            AllSpkzsxzExample goodsExtensionAttributeExample=new AllSpkzsxzExample();
            AllSpkzsxzExample.Criteria criteria = goodsExtensionAttributeExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            extensionAttributeMapper.deleteByExample(goodsExtensionAttributeExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
