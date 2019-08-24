package com.yjh.admi.service;

import com.yjh.db.dao.AllSpssbkMapper;
import com.yjh.db.domain.AllSpssbk;
import com.yjh.db.domain.AllSpssbkExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    @Autowired
    AllSpssbkMapper allSpssbkMapper;

    public List<AllSpssbk> findAllBlock() {
        try {
            AllSpssbkExample allSpssbkExample = new AllSpssbkExample();
            return allSpssbkMapper.selectByExample(allSpssbkExample);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String findByBlockId(Integer id) {
        try {
            return allSpssbkMapper.selectByPrimaryKeySelective(id, AllSpssbk.Column.ssbk).getSsbk();
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }

    public String getblockName(Integer id) {
        try {
            return allSpssbkMapper.selectByPrimaryKeySelective(id, AllSpssbk.Column.ssbk).getSsbk();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
