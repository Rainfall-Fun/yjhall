package com.yjh.db.service;

import com.yjh.db.dao.SysYhlbMapper;
import com.yjh.db.domain.SysYhlb;
import com.yjh.db.domain.SysYhlbExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/1 22:11
 */
@Service
public class UserCategoryService {
    @Autowired
    SysYhlbMapper userCategoryMapper;

    /**
     * 得到所有的用户类别信息
     * @return
     */
    public List<SysYhlb> getAllUserCategories(){
        try {
            return userCategoryMapper.selectByExample(new SysYhlbExample());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
