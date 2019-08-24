package com.yjh.db.service;

import com.yjh.db.dao.XsDdMapper;
import com.yjh.db.domain.XsDd;
import com.yjh.db.domain.XsDdExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class OrderService {
    @Autowired
    XsDdMapper orderMapper;

    /**
     * 得到所有的订单数量
     * @return
     */
    public long countOrders(){
        XsDdExample orderExample=new XsDdExample();
        XsDdExample.Criteria criteria = orderExample.createCriteria();
        return orderMapper.countByExample(orderExample);
    }
}
