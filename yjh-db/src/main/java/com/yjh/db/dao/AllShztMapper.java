package com.yjh.db.dao;

import com.yjh.db.domain.AllShzt;
import com.yjh.db.domain.AllShztExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AllShztMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    long countByExample(AllShztExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    int deleteByExample(AllShztExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer shztbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    int insert(AllShzt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    int insertSelective(AllShzt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    AllShzt selectOneByExample(AllShztExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    AllShzt selectOneByExampleSelective(@Param("example") AllShztExample example, @Param("selective") AllShzt.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    List<AllShzt> selectByExampleSelective(@Param("example") AllShztExample example, @Param("selective") AllShzt.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    List<AllShzt> selectByExample(AllShztExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    AllShzt selectByPrimaryKeySelective(@Param("shztbm") Integer shztbm, @Param("selective") AllShzt.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    AllShzt selectByPrimaryKey(Integer shztbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") AllShzt record, @Param("example") AllShztExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") AllShzt record, @Param("example") AllShztExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AllShzt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AllShzt record);
}