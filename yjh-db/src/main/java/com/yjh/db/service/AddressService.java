package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.AllShdzMapper;
import com.yjh.db.domain.AllShdz;
import com.yjh.db.domain.AllShdzExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/9 17:12
 * 收货地址
 */
@Service
public class AddressService {
    @Autowired
    AllShdzMapper addressMapper;

    /**
     * 添加地址信息
     * @param address
     * @return
     */
    public boolean add(AllShdz address){
        try {
            int i = addressMapper.insertSelective(address);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除地址信息
     * @param addressId
     * @return
     */
    public boolean delete(int addressId){
        try {
            int i = addressMapper.deleteByPrimaryKey(addressId);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改地址信息
     * @param address
     * @return
     */
    public boolean update(AllShdz address){
        try {
            AllShdzExample addressExample=new AllShdzExample();
            AllShdzExample.Criteria criteria = addressExample.createCriteria();
            criteria.andDzbmEqualTo(address.getDzbm());
//            int i = addressMapper.updateByExample(address, addressExample);
            int i = addressMapper.updateByPrimaryKey(address);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据用户id得到收货地址信息
     * @param userId
     * @return
     */
    public List<AllShdz> getAddressByUserId(int userId){
        try {
            AllShdzExample addressExample=new AllShdzExample();
            AllShdzExample.Criteria criteria = addressExample.createCriteria();
            criteria.andYhbmEqualTo(userId);
            return addressMapper.selectByExample(addressExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据用户地址id得到用户地址信息
     * @param addressId
     * @return
     */
    public AllShdz getAddressByAddressId(int addressId){
        try {
            return addressMapper.selectByPrimaryKey(addressId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将该用户的默认地址设置为非默认地址
     * @param userId
     * @return
     */
    public boolean resetDefault(int userId){
        try {
            AllShdzExample addressExample=new AllShdzExample();
            AllShdzExample.Criteria criteria = addressExample.createCriteria();
            criteria.andYhbmEqualTo(userId);
            criteria.andSfsmrdzEqualTo(1);
            List<AllShdz> allShdzs = addressMapper.selectByExample(addressExample);
            if(allShdzs.size()==0)
                return true;
            AllShdz address=allShdzs.get(0);
            address.setSfsmrdz(0);
            int i = addressMapper.updateByPrimaryKey(address);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询出符合要求的地址信息
     * @param userId
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<AllShdz> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order) {
        AllShdzExample example = new AllShdzExample();
        AllShdzExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andYhbmEqualTo(userId);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andShdrLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return addressMapper.selectByExample(example);
    }


}
