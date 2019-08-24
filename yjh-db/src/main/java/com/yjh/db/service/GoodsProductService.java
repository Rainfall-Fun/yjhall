package com.yjh.db.service;

import com.yjh.db.dao.AllSpggMapper;
import com.yjh.db.dao.AllSpkcMapper;
import com.yjh.db.domain.AllSpgg;
import com.yjh.db.domain.AllSpggExample;
import com.yjh.db.domain.AllSpkc;
import com.yjh.db.domain.AllSpkcExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/7 15:32
 */

@Service
public class GoodsProductService {
    AllSpkc.Column column[]=new AllSpkc.Column[]{AllSpkc.Column.spkcbm, AllSpkc.Column.xlggz, AllSpkc.Column.kc, AllSpkc.Column.spsj, AllSpkc.Column.cbj, AllSpkc.Column.jfzddked};
    @Autowired
    AllSpkcMapper goodsProductMapper;

    /**
     * 添加商品规格到数据库
     * @param goodsProduct
     * @return
     */
    public int add(AllSpkc goodsProduct){
        try {
            goodsProductMapper.insertSelective(goodsProduct);
            return goodsProduct.getSpkcbm();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据商品编码删除商品库存
     * @param goodsId
     * @return
     */
    public boolean deleteByGoodsId(int goodsId){
        try {
            AllSpkcExample goodsProductExample=new AllSpkcExample();
            AllSpkcExample.Criteria criteria = goodsProductExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            goodsProductMapper.deleteByExample(goodsProductExample);
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据商品id得到该商品的所有规格
     * @param goodsId
     * @return
     */
    public List<AllSpkc> getByGoodsId(int goodsId){
        try {
            AllSpkcExample goodsProductExample=new AllSpkcExample();
            AllSpkcExample.Criteria criteria = goodsProductExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            return goodsProductMapper.selectByExampleSelective(goodsProductExample,column);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据库存id获得此货品的库存信息
     * @param productsId
     * @return
     */
    public AllSpkc getGoodsProducts(int productsId){
        try {
            return goodsProductMapper.selectByPrimaryKey(productsId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
