package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.HyHyjbxxMapper;
import com.yjh.db.domain.HyHyjbxx;
import com.yjh.db.domain.HyHyjbxxExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/8 21:36
 * 会员基本信息Service
 */
@Service
public class MemberBasicInfoService {
    @Autowired
    HyHyjbxxMapper memberBasicInfoMapper;

    /**
     * 添加会员基本信息
     * @param memberBasicInfo
     * @return
     */
    public boolean add(HyHyjbxx memberBasicInfo){
        try {
            int i = memberBasicInfoMapper.insertSelective(memberBasicInfo);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据用户名获得用户信息
     * @param userName
     * @return
     */
    public HyHyjbxx getMemberInfoByUserName(String userName){
        try{
            HyHyjbxxExample userExample=new HyHyjbxxExample();
            HyHyjbxxExample.Criteria criteria = userExample.createCriteria();
            criteria.andYhmEqualTo(userName);
            List<HyHyjbxx> users = memberBasicInfoMapper.selectByExample(userExample);
            HyHyjbxx user1 = users.get(0);
            return user1;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据用户编号获得会员信息
     * @param userId
     * @return
     */
    public HyHyjbxx getMemberInfoById(int userId){
        try {
            return memberBasicInfoMapper.selectByPrimaryKey(userId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到用户基本信息
     * @param username
     * @param mobile
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<HyHyjbxx> querySelective(String username, String mobile, Integer page, Integer size, String sort, String order) {
        HyHyjbxxExample example = new HyHyjbxxExample();
        HyHyjbxxExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andYhmLike("%" + username + "%");
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andLxsj1EqualTo(mobile);
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return memberBasicInfoMapper.selectByExample(example);
    }
}
