package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.AllKzsxMapper;
import com.yjh.db.domain.AllKzsx;
import com.yjh.db.domain.AllKzsxExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/4 22:56
 */
@Service
public class ExtensionAttributeService {
    @Autowired
    AllKzsxMapper extensionAttributeMapper;
    /**
     * 得到list extensionAttribute里面的所有的扩展属性值
     * @param extensionAttributeIds
     * @return
     */
    public List<AllKzsx> getAllExtensionAttributeBy(List<Integer> extensionAttributeIds){
        try{
            AllKzsxExample extensionAttributeExample=new AllKzsxExample();
            AllKzsxExample.Criteria criteria = extensionAttributeExample.createCriteria();
            criteria.andKzsxbmIn(extensionAttributeIds);
            return extensionAttributeMapper.selectByExample(extensionAttributeExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 得到扩展属性
     * @param goodsSn
     * @param name
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<AllKzsx> querySelective(String goodsSn, String name, Integer page, Integer size, String sort, String order) {
        AllKzsxExample extensionAttriteExample=new AllKzsxExample();
        AllKzsxExample.Criteria criteria = extensionAttriteExample.createCriteria();

        if (!StringUtils.isEmpty(goodsSn)) {
            criteria.andKzsxbmEqualTo(new Integer(goodsSn));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andSxmcLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            extensionAttriteExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return extensionAttributeMapper.selectByExample(extensionAttriteExample);
    }

    /**
     * 修改扩展属性值
     * @param extensionAttribute
     * @return
     */
    public boolean update(AllKzsx extensionAttribute){
        try {
            int i = extensionAttributeMapper.updateByPrimaryKeySelective(extensionAttribute);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 删除扩展属性值
     * @param id
     * @return
     */
    public boolean delete(int id){
        try {
            int i = extensionAttributeMapper.deleteByPrimaryKey(id);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加扩展属性值
     * @param extensionAttribute
     * @return
     */
    public int add(AllKzsx extensionAttribute){
        try {
            extensionAttributeMapper.insertSelective(extensionAttribute);
            return extensionAttribute.getKzsxbm();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 得到所有的扩展属性
     * @return
     */
    public List<AllKzsx> getALlExtensionAttribute(){
        return extensionAttributeMapper.selectByExample(new AllKzsxExample());
    }

    /**
     * 根据扩展属性id得到扩展属性值
     * @param id
     * @return
     */
    public AllKzsx getExtensionAttributeByid(int id){
        return extensionAttributeMapper.selectByPrimaryKey(id);
    }
}
