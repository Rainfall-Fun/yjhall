package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.AllYfgzMapper;
import com.yjh.db.dao.AllYfgzgcMapper;
import com.yjh.db.domain.AllYfgzgc;
import com.yjh.db.domain.AllYfgzgcExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/2 15:17
 */
@Service
public class ExpressRuleConstitutionService {
    @Autowired
    AllYfgzgcMapper expressRuleConstitutionMapper;

    /**
     * 查询出符合要求的运费规则构成
     * @param costRuleId
     * @param name
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<AllYfgzgc> querySelective(String costRuleId, String name, Integer page, Integer size, String sort, String order) {
        AllYfgzgcExample expressRuleExample=new AllYfgzgcExample();
        AllYfgzgcExample.Criteria criteria = expressRuleExample.createCriteria();

        if (!StringUtils.isEmpty(costRuleId)) {
            criteria.andFygzbmEqualTo(new Integer(costRuleId));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andFygzLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            expressRuleExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return expressRuleConstitutionMapper.selectByExample(expressRuleExample);
    }

    /**
     * 添加运费规则组成，成功赶回添加成功记录的主键
     * @param expressRuleConstitution
     * @return
     */
    public int add(AllYfgzgc expressRuleConstitution){
        try {
            expressRuleConstitutionMapper.insertSelective(expressRuleConstitution);
            return expressRuleConstitution.getFygzbm();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 更新运费规则组成信息
     * @param expressRuleConstitution
     * @return
     */
    public boolean update(AllYfgzgc expressRuleConstitution){
        try {
            int i = expressRuleConstitutionMapper.updateByPrimaryKeySelective(expressRuleConstitution);
            if (i==1)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除运费规则组成信息
     * @param id
     * @return
     */
    public boolean delete(int id){
        try {
            int i = expressRuleConstitutionMapper.deleteByPrimaryKey(id);
            if (i==1)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
