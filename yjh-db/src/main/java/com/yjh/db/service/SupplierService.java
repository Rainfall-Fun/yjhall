package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.AllGysMapper;
import com.yjh.db.domain.AllGys;
import com.yjh.db.domain.AllGysExample;
import com.yjh.db.domain.AllSpjbxx;
import com.yjh.db.domain.AllSpjbxxExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class SupplierService {
    @Autowired
    AllGysMapper supplierMapper;

    /**
     * 得到所有的供应商信息
     * @return
     */
    public List<AllGys> getAllSupplier(){
        AllGysExample supplierExample=new AllGysExample();
        return supplierMapper.selectByExample(supplierExample);
    }

    public List<AllGys> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        AllGysExample supplierExample=new AllGysExample();
        AllGysExample.Criteria criteria = supplierExample.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andGysbmEqualTo(id);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andGysmcEqualTo("%" + name + "%");
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            supplierExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return supplierMapper.selectByExample(supplierExample);
    }

    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    public boolean addSupplier(AllGys supplier){
        try {
            supplierMapper.insertSelective(supplier);
            return true;
        }catch (Exception e){
            e.printStackTrace();;
            return false;
        }
    }

    /**
     * 删除供应商
     * @param supplier
     * @return
     */
    public boolean deleteSupplier(AllGys supplier){
        try {
            supplierMapper.deleteByPrimaryKey(supplier.getGysbm());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 更新供应商
     * @param supplier
     * @return
     */
    public int updateSupplier(AllGys supplier){
        try {
            return supplierMapper.updateByPrimaryKeySelective(supplier);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
