package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.AllKzsxMapper;
import com.yjh.db.dao.AllSplbkzsxMapper;
import com.yjh.db.domain.AllKzsx;
import com.yjh.db.domain.AllKzsxExample;
import com.yjh.db.domain.AllSplbkzsxExample;
import com.yjh.db.domain.AllSplbkzsxKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/4 11:51
 * 商品类别扩展属性service
 */

@Service
public class GoodsCategoryExtensionService {
    @Autowired
    AllSplbkzsxMapper categoryExtensionAttributeMapper;
    /**
     * 根据商品分类编码得到该分类下的所有扩展属性值
     * @param categoryId
     * @return
     */
    public List<Integer> getAllExtensionAttributeByCategoryId(int categoryId){
        try {
            AllSplbkzsxExample categoryExtensionExample=new AllSplbkzsxExample();
            AllSplbkzsxExample.Criteria criteria = categoryExtensionExample.createCriteria();
            criteria.andSpflbmEqualTo(categoryId);
            List<AllSplbkzsxKey> allSplbkzsxKeys = categoryExtensionAttributeMapper.selectByExample(categoryExtensionExample);
            List<Integer> extensionAttributeIds=new ArrayList<>(allSplbkzsxKeys.size());
            for (AllSplbkzsxKey extensionAttributeId :
                    allSplbkzsxKeys) {
                extensionAttributeIds.add(extensionAttributeId.getKzsxbm());
            }
            return extensionAttributeIds;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 删除商品类别扩展属性根据商品类别id和扩展属性id
     * @param categoryId
     * @param extensionId
     * @return
     */
    public boolean delete(int categoryId,int extensionId){
        try {
            AllSplbkzsxExample categoryExtensionExample=new AllSplbkzsxExample();
            AllSplbkzsxExample.Criteria criteria = categoryExtensionExample.createCriteria();
            criteria.andSpflbmEqualTo(categoryId);
            criteria.andKzsxbmEqualTo(extensionId);
            int i = categoryExtensionAttributeMapper.deleteByExample(categoryExtensionExample);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据分类id删除商品类别扩展属性
     * @param categoryId
     * @return
     */
    public boolean delete(int categoryId){
        try {
            AllSplbkzsxExample categoryExtensionExample=new AllSplbkzsxExample();
            AllSplbkzsxExample.Criteria criteria = categoryExtensionExample.createCriteria();
            criteria.andSpflbmEqualTo(categoryId);
            categoryExtensionAttributeMapper.deleteByExample(categoryExtensionExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据扩展属性id删除商品类别扩展属性值
     * @param extensionAttributeId
     * @return
     */
    public boolean deleteByExtensionAttributeId(int extensionAttributeId){
        try {
            AllSplbkzsxExample categoryExtensionExample=new AllSplbkzsxExample();
            AllSplbkzsxExample.Criteria criteria = categoryExtensionExample.createCriteria();
            criteria.andKzsxbmEqualTo(extensionAttributeId);
            categoryExtensionAttributeMapper.deleteByExample(categoryExtensionExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加商品类别扩展属性
     * @param catExtensionAttribute
     * @return
     */
    public boolean add(AllSplbkzsxKey catExtensionAttribute){
        try {
            int insert = categoryExtensionAttributeMapper.insert(catExtensionAttribute);
            return insert==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
