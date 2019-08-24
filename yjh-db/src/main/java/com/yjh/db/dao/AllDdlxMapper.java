package com.yjh.db.dao;

import com.yjh.db.domain.AllDdlx;
import com.yjh.db.domain.AllDdlxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AllDdlxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    long countByExample(AllDdlxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    int deleteByExample(AllDdlxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer ddlxbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    int insert(AllDdlx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    int insertSelective(AllDdlx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    AllDdlx selectOneByExample(AllDdlxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    AllDdlx selectOneByExampleSelective(@Param("example") AllDdlxExample example, @Param("selective") AllDdlx.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    List<AllDdlx> selectByExampleSelective(@Param("example") AllDdlxExample example, @Param("selective") AllDdlx.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    List<AllDdlx> selectByExample(AllDdlxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    AllDdlx selectByPrimaryKeySelective(@Param("ddlxbm") Integer ddlxbm, @Param("selective") AllDdlx.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    AllDdlx selectByPrimaryKey(Integer ddlxbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AllDdlx record, @Param("example") AllDdlxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AllDdlx record, @Param("example") AllDdlxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AllDdlx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ddlx
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AllDdlx record);
}