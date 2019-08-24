package com.yjh.db.service;

import com.yjh.db.dao.AllYwqyMapper;
import com.yjh.db.domain.AllYwqy;
import com.yjh.db.domain.AllYwqyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/31 14:18
 */
@Service
public class BusinessRegionService {
    @Autowired
    AllYwqyMapper businessRegionMapper;

    /**
     * 获得所有的一级区域
     * @return
     */
    public List<AllYwqy> getAllL1Area(){
        try{
            AllYwqyExample regionExample=new AllYwqyExample();
            AllYwqyExample.Criteria criteria = regionExample.createCriteria();
            criteria.andQydjEqualTo(1);
            return businessRegionMapper.selectByExample(regionExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据一级区域编码获得其二级区域编码
     * @param regionId
     * @return
     */
    public List<AllYwqy> getAllL2AreaBySuperiorArea(int regionId){
        try{
            AllYwqyExample regionExample=new AllYwqyExample();
            AllYwqyExample.Criteria criteria = regionExample.createCriteria();
            criteria.andQydjEqualTo(2);
            criteria.andSjqybmEqualTo(regionId);
            return businessRegionMapper.selectByExample(regionExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据二级区域编码获得其三级区域编码
     * @param regionId
     * @return
     */
    public List<AllYwqy> getAllL3AreaBySuperiorArea(int regionId){
        try {
            AllYwqyExample regionExample=new AllYwqyExample();
            AllYwqyExample.Criteria criteria = regionExample.createCriteria();
            criteria.andQydjEqualTo(3);
            criteria.andSjqybmEqualTo(regionId);
            return businessRegionMapper.selectByExample(regionExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到所有的业务区域
     * @return
     */
    public List<AllYwqy> getAllBusinessRegionInfo(){
        try{
            AllYwqyExample regionExample=new AllYwqyExample();
            return businessRegionMapper.selectByExample(regionExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
