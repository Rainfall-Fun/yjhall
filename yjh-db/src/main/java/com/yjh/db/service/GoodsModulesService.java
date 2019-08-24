package com.yjh.db.service;

import com.yjh.db.dao.AllSpssbkMapper;
import com.yjh.db.domain.AllSpssbk;
import com.yjh.db.domain.AllSpssbkExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class GoodsModulesService {
    @Autowired
    AllSpssbkMapper goodsModulesMapper;

    /**
     * 得到所有的商品板块信息
     * @return
     */
    public List<AllSpssbk> getAllGoodsModules(){
        return goodsModulesMapper.selectByExample(new AllSpssbkExample());
    }
}
