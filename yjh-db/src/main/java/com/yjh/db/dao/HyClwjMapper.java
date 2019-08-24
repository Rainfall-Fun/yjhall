package com.yjh.db.dao;

import com.yjh.db.domain.HyClwj;
import com.yjh.db.domain.HyClwjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HyClwjMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    long countByExample(HyClwjExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    int deleteByExample(HyClwjExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long clwjbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    int insert(HyClwj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    int insertSelective(HyClwj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    HyClwj selectOneByExample(HyClwjExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    HyClwj selectOneByExampleSelective(@Param("example") HyClwjExample example, @Param("selective") HyClwj.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    List<HyClwj> selectByExampleSelective(@Param("example") HyClwjExample example, @Param("selective") HyClwj.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    List<HyClwj> selectByExample(HyClwjExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    HyClwj selectByPrimaryKeySelective(@Param("clwjbm") Long clwjbm, @Param("selective") HyClwj.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    HyClwj selectByPrimaryKey(Long clwjbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HyClwj record, @Param("example") HyClwjExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HyClwj record, @Param("example") HyClwjExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HyClwj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_clwj
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HyClwj record);
}