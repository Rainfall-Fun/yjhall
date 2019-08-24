package com.yjh.db.service;

import com.yjh.db.dao.HyHyztMapper;
import com.yjh.db.domain.HyHyzt;
import com.yjh.db.domain.HyHyztExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/10 10:32
 */
@Service
public class MemberStatusService {
    @Autowired
    HyHyztMapper memberStatusMapper;

    /**
     * 获得等级信息，根据id
     * @param memberStatusId
     * @return
     */
    public HyHyzt getByMemberStatusId(int memberStatusId){
        try {
            return memberStatusMapper.selectByPrimaryKey(memberStatusId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean add(HyHyzt memberStatus){
        try {
            int i = memberStatusMapper.insertSelective(memberStatus);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id){
        try {
            int i = memberStatusMapper.deleteByPrimaryKey(id);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(HyHyzt memberStatus){
        try {
            int i = memberStatusMapper.updateByPrimaryKey(memberStatus);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<HyHyzt> getAllMemberStatus(){
        try {
            return memberStatusMapper.selectByExample(new HyHyztExample());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
