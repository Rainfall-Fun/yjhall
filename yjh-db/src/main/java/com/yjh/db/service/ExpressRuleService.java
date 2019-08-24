package com.yjh.db.service;

import com.yjh.db.dao.AllYfgzMapper;
import com.yjh.db.domain.AllYfgz;
import com.yjh.db.domain.AllYfgzExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/2 15:58
 */
@Service
public class ExpressRuleService {
    @Autowired
    AllYfgzMapper expressRuleMapper;

    /**
     * 得到所有的运费规则
     * @return
     */
    public List<AllYfgz> getAllExpressRules(){
        return expressRuleMapper.selectByExample(new AllYfgzExample());
    }

    /**
     * 根据id得到运费规则
     * @param id
     * @return
     */
    public AllYfgz getExpressRuleById(int id){
        return expressRuleMapper.selectByPrimaryKey(id);
    }

}
