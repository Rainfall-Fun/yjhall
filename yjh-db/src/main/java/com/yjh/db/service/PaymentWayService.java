package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.AllZffsMapper;
import com.yjh.db.domain.AllSppp;
import com.yjh.db.domain.AllSpppExample;
import com.yjh.db.domain.AllZffs;
import com.yjh.db.domain.AllZffsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/29 21:53
 */
@Service
public class PaymentWayService {
    /**
     * 得到所有支付方式
     */
    @Autowired
    AllZffsMapper paymentWayMapper;
    public List<AllZffs> getAllPaymentWays(){
        return paymentWayMapper.selectByExample(new AllZffsExample());
    }


    /**
     * 查询出符合条件的支付方式
     * @param paymentWayId
     * @param name
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<AllZffs> querySelective(String paymentWayId, String name, Integer page, Integer size, String sort, String order) {
        AllZffsExample paymentWayExample=new AllZffsExample();
        AllZffsExample.Criteria criteria = paymentWayExample.createCriteria();

        if (!StringUtils.isEmpty(paymentWayId)) {
            criteria.andZffsbmEqualTo(Integer.valueOf(paymentWayId));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andZffsLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            paymentWayExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return paymentWayMapper.selectByExample(paymentWayExample);
    }

    /**
     * 判断支付方式名称是否存在，存在返回true
     * @param paymentWayName
     * @return
     */
    public boolean checkPaymentWay(String paymentWayName){
        AllZffsExample paymentWayExample=new AllZffsExample();
        AllZffsExample.Criteria criteria = paymentWayExample.createCriteria();
        criteria.andZffsEqualTo(paymentWayName);
        List<AllZffs> allZffs = paymentWayMapper.selectByExample(paymentWayExample);
        if (allZffs.size()!=0)
            return true;
        else
            return false;
    }

    /**
     * 添加支付方式，成功返回添加记录的主键，不成功返回0
     * @param paymentWay
     * @return
     */
    public int addPaymentWay(AllZffs paymentWay){
        try {
            paymentWayMapper.insertSelective(paymentWay);
            return paymentWay.getZffsbm();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除支付方式，成功返回true
     * @param paymentId
     * @return
     */
    public boolean deletePayment(int paymentId){
        try{
            int i = paymentWayMapper.deleteByPrimaryKey(paymentId);
            if(i==1)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新支付方式，成功返回true
     * @param paymentWay
     * @return
     */
    public boolean updatePaymentWay(AllZffs paymentWay){
        try {
            int i = paymentWayMapper.updateByPrimaryKeySelective(paymentWay);
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
