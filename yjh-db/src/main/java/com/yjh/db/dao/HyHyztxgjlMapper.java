package com.yjh.db.dao;

import com.yjh.db.domain.HyHyztxgjl;
import com.yjh.db.domain.HyHyztxgjlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HyHyztxgjlMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    long countByExample(HyHyztxgjlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    int deleteByExample(HyHyztxgjlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer hyztxgjlbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    int insert(HyHyztxgjl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    int insertSelective(HyHyztxgjl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    HyHyztxgjl selectOneByExample(HyHyztxgjlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    HyHyztxgjl selectOneByExampleSelective(@Param("example") HyHyztxgjlExample example, @Param("selective") HyHyztxgjl.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    List<HyHyztxgjl> selectByExampleSelective(@Param("example") HyHyztxgjlExample example, @Param("selective") HyHyztxgjl.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    List<HyHyztxgjl> selectByExample(HyHyztxgjlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    HyHyztxgjl selectByPrimaryKeySelective(@Param("hyztxgjlbm") Integer hyztxgjlbm, @Param("selective") HyHyztxgjl.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    HyHyztxgjl selectByPrimaryKey(Integer hyztxgjlbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HyHyztxgjl record, @Param("example") HyHyztxgjlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HyHyztxgjl record, @Param("example") HyHyztxgjlExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HyHyztxgjl record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_hyztxgjl
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HyHyztxgjl record);
}