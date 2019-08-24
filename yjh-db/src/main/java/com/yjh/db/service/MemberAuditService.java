package com.yjh.db.service;

import com.yjh.db.dao.AllShztMapper;
import com.yjh.db.domain.AllShzt;
import com.yjh.db.domain.AllShztExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/10 10:43
 */
@Service
public class MemberAuditService {
    @Autowired
    AllShztMapper memberAuditMapper;

    public boolean add(AllShzt auditStatus){
        try {
            int i = memberAuditMapper.insertSelective(auditStatus);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id){
        try {
            int i = memberAuditMapper.deleteByPrimaryKey(id);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(AllShzt auditStatus){
        try {
            int i = memberAuditMapper.updateByPrimaryKey(auditStatus);
            return i==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public AllShzt getById(int auditStatusId){
        try {
            return memberAuditMapper.selectByPrimaryKey(auditStatusId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到所有的审核信息
     * @return
     */
    public List<AllShzt> getAllMemberAudits(){
        try {
            return memberAuditMapper.selectByExample(new AllShztExample());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
