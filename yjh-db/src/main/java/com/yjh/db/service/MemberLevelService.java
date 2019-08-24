package com.yjh.db.service;

import com.yjh.db.dao.AllHydjMapper;
import com.yjh.db.domain.AllHydj;
import com.yjh.db.domain.AllHydjExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/10 10:10
 */
@Service
public class MemberLevelService {
    @Autowired
    AllHydjMapper memberLevelMapper;

    /**
     * 添加会员等级信息
     * @param memberLevel
     * @return
     */
    public boolean add(AllHydj memberLevel){
        try {
            int i = memberLevelMapper.insertSelective(memberLevel);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 获得会员等级信息
     * @param memberLevelId
     * @return
     */
    public AllHydj getByMemberLevelId(int memberLevelId){
        try {
            return memberLevelMapper.selectByPrimaryKey(memberLevelId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除会员等级信息
     * @param memberLevelId
     * @return
     */
    public boolean delete(int memberLevelId){
        try {
            int i = memberLevelMapper.deleteByPrimaryKey(memberLevelId);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改会员等级信息
     * @param memberLevel
     * @return
     */
    public boolean update(AllHydj memberLevel){
        try {
            int i = memberLevelMapper.updateByPrimaryKey(memberLevel);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<AllHydj> getAllMemberLevel(){
        try {
            return memberLevelMapper.selectByExample(new AllHydjExample());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
