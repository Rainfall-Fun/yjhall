package com.yjh.db.dao;

import com.yjh.db.domain.SysYhlb;
import com.yjh.db.domain.SysYhlbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysYhlbMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    long countByExample(SysYhlbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    int deleteByExample(SysYhlbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer yhlbbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    int insert(SysYhlb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    int insertSelective(SysYhlb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    SysYhlb selectOneByExample(SysYhlbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    SysYhlb selectOneByExampleSelective(@Param("example") SysYhlbExample example, @Param("selective") SysYhlb.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    List<SysYhlb> selectByExampleSelective(@Param("example") SysYhlbExample example, @Param("selective") SysYhlb.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    List<SysYhlb> selectByExample(SysYhlbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    SysYhlb selectByPrimaryKeySelective(@Param("yhlbbm") Integer yhlbbm, @Param("selective") SysYhlb.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    SysYhlb selectByPrimaryKey(Integer yhlbbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysYhlb record, @Param("example") SysYhlbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysYhlb record, @Param("example") SysYhlbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysYhlb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_yhlb
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysYhlb record);
}