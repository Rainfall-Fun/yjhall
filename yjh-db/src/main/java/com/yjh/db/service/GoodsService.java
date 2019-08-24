package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.AllSpjbxxMapper;
import com.yjh.db.domain.AllSpjbxx;
import com.yjh.db.domain.AllSpjbxxExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class GoodsService {
    AllSpjbxx.Column[] columns = new AllSpjbxx.Column[]{AllSpjbxx.Column.spbm, AllSpjbxx.Column.spmc, AllSpjbxx.Column.scj, AllSpjbxx.Column.spsj};

    @Autowired
    AllSpjbxxMapper goodsMapper;

    /**
     * 得到所有的种类商品数量
     * @return
     */
    public long countGoods(){
        AllSpjbxxExample goodsExample=new AllSpjbxxExample();
        AllSpjbxxExample.Criteria criteria = goodsExample.createCriteria();

        return goodsMapper.countByExample(goodsExample);
    }


    /**
     * 查询符合条件的商品
     * @param goodsSn
     * @param name
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<AllSpjbxx> querySelective(String goodsSn, String name, Integer page, Integer size, String sort, String order) {
        AllSpjbxxExample goodsExample=new AllSpjbxxExample();
        AllSpjbxxExample.Criteria criteria = goodsExample.createCriteria();

        if (!StringUtils.isEmpty(goodsSn)) {
            criteria.andSpbmEqualTo(new Integer(goodsSn));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andSpmcLike("%" + name + "%");
        }

        //是否删除
//        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            goodsExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return goodsMapper.selectByExample(goodsExample);
    }

    /**
     * 根据品牌id查询出商品信息
     * @param brandId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<AllSpjbxx> querySelectiveByBrandId(Integer categoryId,Integer brandId, Integer page, Integer size, String sort, String order) {
        AllSpjbxxExample goodsExample=new AllSpjbxxExample();
        AllSpjbxxExample.Criteria criteria = goodsExample.createCriteria();

        if (!StringUtils.isEmpty(brandId)) {
            criteria.andSpppbmEqualTo(brandId);
        }
        if(!StringUtils.isEmpty(categoryId))
            criteria.andSpflbmLike("%"+categoryId+"%");

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            goodsExample.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return goodsMapper.selectByExample(goodsExample);
    }


    /**
     * 根据商品名称判断是否存在
     * @param goodsName
     * @return
     */
    public boolean checkExistByName(String goodsName){
        AllSpjbxxExample goodsExample=new AllSpjbxxExample();
        AllSpjbxxExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andSpmcEqualTo(goodsName);
        List<AllSpjbxx> goods = goodsMapper.selectByExample(goodsExample);
        if (goods.size()>0)
            return true;
        else
            return false;

    }

    /**
     * 添加商品信息
     * @param goods
     * @return 成功返回商品插入之后的id
     */
    public int addGoods(AllSpjbxx goods){
        try{
            goodsMapper.insertSelective(goods);
            return goods.getSpbm();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public boolean deleteGoods(Integer goodsId){
        try {
            AllSpjbxxExample goodsExample=new AllSpjbxxExample();
            AllSpjbxxExample.Criteria criteria = goodsExample.createCriteria();
            criteria.andSpbmEqualTo(goodsId);
            goodsMapper.deleteByExample(goodsExample);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public AllSpjbxx getGoodsByGoodsId(Integer goodsId){
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    public boolean updateGoodsInfo(AllSpjbxx goodsInfo){
        try {
            goodsMapper.updateByPrimaryKeySelective(goodsInfo);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取推荐上市
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<AllSpjbxx> queryByRecommendation(int offset, int limit) {
        AllSpjbxxExample example = new AllSpjbxxExample();
        example.or().andSfstjspEqualTo(1).andSfsjEqualTo(1);
        PageHelper.startPage(offset, limit);

        return goodsMapper.selectByExampleSelective(example, columns);
    }
}
