package com.yjh.db.service;

import com.yjh.db.dao.AllSpflMapper;
import com.yjh.db.domain.AllSpfl;
import com.yjh.db.domain.AllSpflExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class GoodsCategoryService {
    @Autowired
    AllSpflMapper goodsCategoryMapper;

    /**
     * 根据板块id得到该板块下的所有的一级商品分类
     * @return
     */
    public List<AllSpfl> getGoodsL1Categories(){
        AllSpflExample goodsCategoryExample=new AllSpflExample();
        AllSpflExample.Criteria criteria = goodsCategoryExample.createCriteria();
        criteria.andFldjEqualTo(1);
        return goodsCategoryMapper.selectByExample(goodsCategoryExample);

    }

    /**
     * 得到二级分类
     * @param superiorCategoryId
     * @return
     */
    public List<AllSpfl> getGoodsL2CategoriesBySuperiorCategoryId(int superiorCategoryId){
        AllSpflExample goodsCategoryExample=new AllSpflExample();
        AllSpflExample.Criteria criteria = goodsCategoryExample.createCriteria();
        criteria.andFldjEqualTo(2);
        criteria.andSjspflbmEqualTo(superiorCategoryId);
        return goodsCategoryMapper.selectByExample(goodsCategoryExample);
    }

    /**
     * 得到三级分类
     * @param superiorCategoryId
     * @return
     */
    public List<AllSpfl> getGoodsL3CategoriesBySuperiorCategoryId(int superiorCategoryId){
        AllSpflExample goodsCategoryExample=new AllSpflExample();
        AllSpflExample.Criteria criteria = goodsCategoryExample.createCriteria();
        criteria.andFldjEqualTo(3);
        criteria.andSjspflbmEqualTo(superiorCategoryId);
        return goodsCategoryMapper.selectByExample(goodsCategoryExample);
    }

    /**
     * 根据分类id获得分类信息
     * @param id
     * @return
     */
    public AllSpfl getGoodsCategoryById(int id){
        try {
            return goodsCategoryMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    /**
     * 添加分类
     *
     * @param allSpfl 分类对象
     * @return 成功返回true, 失败返回false
     */
    public boolean addGoodsCategory(AllSpfl allSpfl) {
        try {
            goodsCategoryMapper.insertSelective(allSpfl);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据分类分级查询分类对象
     *
     * @param level 分类等级
     * @return 分类对象列表
     */
    public List<AllSpfl> findByCategoryLevel(int level) {
        try {
            AllSpflExample allSpflExample = new AllSpflExample();
            allSpflExample.or().andFldjEqualTo(level);
            return goodsCategoryMapper.selectByExample(allSpflExample);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 根据父分类ID找分类对象
     *
     * @param superiorId 父分类ID
     * @return 分类对象
     */
    public List<AllSpfl> findByCategorySuperiorId(int superiorId) {
        try {
            AllSpflExample allSpflExample = new AllSpflExample();
            allSpflExample.or().andSjspflbmEqualTo(superiorId);
            return goodsCategoryMapper.selectByExample(allSpflExample);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得分类子类的深度（用于遍历所有的深度的子分类）
     *
     * @return 最深的分类数
     */
    public int getCategoryDepth() {
        try {
            int result;
            AllSpflExample allSpflExample = new AllSpflExample();
            allSpflExample.setDistinct(true);//去重
            result = goodsCategoryMapper.selectByExampleSelective(allSpflExample, AllSpfl.Column.fldj).size();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 按照id查找
     *
     * @param id
     * @return
     */
    public AllSpfl findById(Integer id) {
        try {
            return goodsCategoryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 按照父id查找
     *
     * @param id
     * @return
     */
    public String findSuperiorNameBySuperiorId(Integer id) {
        try {
            AllSpflExample allSpflExample = new AllSpflExample();
            allSpflExample.or().andSpflbmEqualTo(id);
            return goodsCategoryMapper.selectByPrimaryKey(id).getSpfl();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取父分类树节点
     *
     * @param id
     * @return
     */
    public String[] getCategoryTree(Integer id) {

        if (id == 0) return new String[]{"0"};

        AllSpfl allSpfl = this.findById(id);
        int pid = allSpfl.getSjspflbm();
        List<String> multi = new ArrayList<>();
        if (pid == 0) return new String[]{"0"};
        else {
            pid = id;
            multi.add(pid + "");
            do {
                pid = this.findById(pid).getSjspflbm();
                multi.add(pid + "");

            } while (pid != 0);
        }

        Collections.reverse(multi);

        String[] res = new String[multi.size()];
        for (int i = 0; i < multi.size(); i++) {
            res[i] = multi.get(i);
        }

        return res;
    }

    /**
     * 根据主键更新
     *
     * @param allSpfl
     * @return
     */
    public boolean updateById(AllSpfl allSpfl) {
        try {
            goodsCategoryMapper.updateByPrimaryKey(allSpfl);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id) {
        try {
            goodsCategoryMapper.deleteByPrimaryKey(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllChildrenById(Integer id) {
        Integer[] ids = findAllChildrenId(id);
        try {
            for (Integer i : ids) {
                this.deleteById(i);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 找到所有的子节（包括自己）
     *
     * @param id
     * @return
     */
    private Integer[] findAllChildrenId(Integer id) {

        List<Integer> result = new ArrayList<>();
        result.add(id);

        List<AllSpfl> allSpfls1 = this.findByCategorySuperiorId(id);
        for (AllSpfl allSpfl1 : allSpfls1) {
            Integer id1 = allSpfl1.getSpflbm();
            result.add(id1);

            List<AllSpfl> allSpfls2 = this.findByCategorySuperiorId(id1);
            for (AllSpfl allSpfl2 : allSpfls2) {
                Integer id2 = allSpfl2.getSpflbm();

                result.add(id2);

                List<AllSpfl> allSpfls3 = this.findByCategorySuperiorId(id2);
                for (AllSpfl allSpfl3 : allSpfls3) {
                    Integer id3 = allSpfl3.getSpflbm();

                    result.add(id3);

                    List<AllSpfl> allSpfls4 = this.findByCategorySuperiorId(id3);
                    for (AllSpfl allSpfl4 : allSpfls4) {
                        Integer id4 = allSpfl4.getSpflbm();

                        result.add(id4);
                    }
                }
            }

        }

        Integer[] ids = new Integer[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ids[i] = result.get(i);
        }
        return ids;
    }


    /**
     * 检查是否到分类的子节点的分类名是否有重复
     *
     * @param id   父id
     * @param data 需要检查的分类名的字符串
     * @return 有重复返回true 没有重复返回false
     */
    public boolean isUnderTreeRepeatedById(Integer id, String data) {

        //一级分类的情况
        if (id == 0) {
            List<AllSpfl> allSpfls = this.findByCategoryLevel(1);
            for (AllSpfl allSpfl : allSpfls) {
                if (allSpfl.getSpfl().equals(data)) {
                    return true;
                }
            }
            return false;
        } else {
            //二级以上分类的情况
            AllSpfl allSpfl;
            try {
                allSpfl = this.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
            if (data.equals(allSpfl.getSpfl())) {
                return true;
            }
            String tmpString;
            AllSpfl tmp = allSpfl;
            while (true) {
                if (tmp.getSjspflbm() == 0) {
                    return false;
                }
                tmp = this.findById(tmp.getSjspflbm());
                tmpString = tmp.getSpfl();
                if (tmpString.equals(data)) {
                    return true;
                }
            }
        }
    }
}
