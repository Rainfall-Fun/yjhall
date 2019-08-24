package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.SysYhMapper;
import com.yjh.db.domain.SysYh;
import com.yjh.db.domain.SysYhExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class UserService {
    @Autowired
    SysYhMapper sysYhMapper;
    /**
     * 判断用户名是否正确
     * @param userName
     * @param password
     * @return true:正确
     */
    public boolean judgePasswordIsCorrect(String userName,String password){
        SysYhExample userExample=new SysYhExample();
        SysYhExample.Criteria criteria = userExample.createCriteria();
        criteria.andYhmEqualTo(userName);
        List<SysYh> users = sysYhMapper.selectByExample(userExample);
        try{
            if(users.get(0).getMm().equals(password))
                return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 添加一个用户
     * @param user
     * @return 成功返回true
     */
    public boolean addUser(SysYh user){
        int result1 = sysYhMapper.insertSelective(user);
        if (result1==1)
            return true;
        else
            return false;
    }

    /**
     * 修改用户信息
     * @param user //至少含有用户实体主键的一个对象
     * @return
     */
    public boolean modifyUser(SysYh user){
        int result = sysYhMapper.updateByPrimaryKeySelective(user);
        if (result==1)
            return true;
        else
            return false;
    }

    /**
     * 根据用户名查出用户信息
     * @param userName
     * @return
     */
    public List<SysYh> getUsers(String userName){
        SysYhExample userExample=new SysYhExample();
        SysYhExample.Criteria criteria = userExample.createCriteria();
        criteria.andYhmEqualTo(userName);
        List<SysYh> users = sysYhMapper.selectByExample(userExample);
        return users;
    }

    /**
     * 判断用户名是否存在
     * @param userName
     * @return //存在返回true
     */
    public boolean checkExistByName(String userName){
        SysYhExample userExample=new SysYhExample();
        SysYhExample.Criteria criteria = userExample.createCriteria();
        criteria.andYhmEqualTo(userName);
        List<SysYh> users = sysYhMapper.selectByExample(userExample);
        if (users.size()>0)
            return true;
        else
            return false;
    }

    /**
     * 得到所有的用户总数
     * @return
     */
    public long countUsers(){
        SysYhExample userExample=new SysYhExample();
        SysYhExample.Criteria criteria = userExample.createCriteria();
        long count = sysYhMapper.countByExample(userExample);
        return count;
    }


    public List<SysYh> querySelective(String name, Integer page, Integer size, String sort, String order) {
        SysYhExample sysYhExample=new SysYhExample();
        SysYhExample.Criteria criteria = sysYhExample.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andYhmLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            sysYhExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return sysYhMapper.selectByExample(sysYhExample);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public boolean deleteUser(String userId){
        try {
            if(sysYhMapper.deleteByPrimaryKey(userId)==1)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据用户类别编码得到改类别的用户
     * @param typeId
     * @return
     */
    public List<SysYh> getUsersByUserType(int typeId){
        try {
            SysYhExample userExample=new SysYhExample();
            SysYhExample.Criteria criteria = userExample.createCriteria();
            criteria.andYhlbbmEqualTo(typeId);
            return sysYhMapper.selectByExample(userExample);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



}
