package com.yjh.db.service;

import com.yjh.db.domain.AllSpgg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by 彭椿悦 on 2019/7/29 17:30
 */
public class GoodsSpecificationServiceTest {
    @Autowired GoodsSpecificationService specificationService;
    @Test
    public void addGoodsSpecification() {
        AllSpgg goodsSpecification=new AllSpgg();
        goodsSpecification.setSpbm(1);
        goodsSpecification.setGgmc(1+"");
        int i = specificationService.addGoodsSpecification(goodsSpecification);
        System.out.println(i);
    }
}