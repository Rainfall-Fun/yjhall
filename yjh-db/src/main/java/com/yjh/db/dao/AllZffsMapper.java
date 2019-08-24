package com.yjh.db.dao;

import com.yjh.db.domain.AllZffs;
import com.yjh.db.domain.AllZffsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AllZffsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    long countByExample(AllZffsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    int deleteByExample(AllZffsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer zffsbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    int insert(AllZffs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    int insertSelective(AllZffs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    AllZffs selectOneByExample(AllZffsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    AllZffs selectOneByExampleSelective(@Param("example") AllZffsExample example, @Param("selective") AllZffs.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    List<AllZffs> selectByExampleSelective(@Param("example") AllZffsExample example, @Param("selective") AllZffs.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    List<AllZffs> selectByExample(AllZffsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    AllZffs selectByPrimaryKeySelective(@Param("zffsbm") Integer zffsbm, @Param("selective") AllZffs.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    AllZffs selectByPrimaryKey(Integer zffsbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AllZffs record, @Param("example") AllZffsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AllZffs record, @Param("example") AllZffsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AllZffs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_zffs
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AllZffs record);
}