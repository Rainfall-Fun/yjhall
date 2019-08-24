package com.yjh.db.dao;

import com.yjh.db.domain.GoodsSpecification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/6 14:32
 */
@Repository
public interface GoodsSpecificationMapper {
    List<GoodsSpecification> getSpecificationByGoodsId(@Param("goodsId") Integer goodsId);
}
