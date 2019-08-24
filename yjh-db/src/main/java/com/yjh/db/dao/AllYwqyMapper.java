package com.yjh.db.dao;

import com.yjh.db.domain.AllYwqy;
import com.yjh.db.domain.AllYwqyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AllYwqyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    long countByExample(AllYwqyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    int deleteByExample(AllYwqyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer ywqybm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    int insert(AllYwqy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    int insertSelective(AllYwqy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    AllYwqy selectOneByExample(AllYwqyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    AllYwqy selectOneByExampleSelective(@Param("example") AllYwqyExample example, @Param("selective") AllYwqy.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    List<AllYwqy> selectByExampleSelective(@Param("example") AllYwqyExample example, @Param("selective") AllYwqy.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    List<AllYwqy> selectByExample(AllYwqyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    AllYwqy selectByPrimaryKeySelective(@Param("ywqybm") Integer ywqybm, @Param("selective") AllYwqy.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    AllYwqy selectByPrimaryKey(Integer ywqybm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AllYwqy record, @Param("example") AllYwqyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AllYwqy record, @Param("example") AllYwqyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AllYwqy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AllYwqy record);
}