package com.yjh.db.service;

import com.yjh.db.dao.AllYfgzMapper;
import com.yjh.db.domain.AllYfgz;
import com.yjh.db.domain.AllYfgzExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/29 22:12
 */
@Service
public class FreightRuleService {
    /**
     * 得到所有的运费规则
     */
    @Autowired
    AllYfgzMapper freightRuleMapper;
    public List<AllYfgz> getAllFreightRule(){
        return freightRuleMapper.selectByExample(new AllYfgzExample());
    }
}
