package com.yjh.db.dao;

import com.yjh.db.domain.SysQx;
import com.yjh.db.domain.SysQxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysQxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    long countByExample(SysQxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    int deleteByExample(SysQxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer qxbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    int insert(SysQx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    int insertSelective(SysQx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    SysQx selectOneByExample(SysQxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    SysQx selectOneByExampleSelective(@Param("example") SysQxExample example, @Param("selective") SysQx.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    List<SysQx> selectByExampleSelective(@Param("example") SysQxExample example, @Param("selective") SysQx.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    List<SysQx> selectByExample(SysQxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    SysQx selectByPrimaryKeySelective(@Param("qxbm") Integer qxbm, @Param("selective") SysQx.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    SysQx selectByPrimaryKey(Integer qxbm);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysQx record, @Param("example") SysQxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysQx record, @Param("example") SysQxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysQx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_qx
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysQx record);
}