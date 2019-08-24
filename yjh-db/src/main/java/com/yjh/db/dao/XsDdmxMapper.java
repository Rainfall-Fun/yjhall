package com.yjh.db.dao;

import com.yjh.db.domain.XsDdmx;
import com.yjh.db.domain.XsDdmxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface XsDdmxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    long countByExample(XsDdmxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    int deleteByExample(XsDdmxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long ddmxh);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    int insert(XsDdmx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    int insertSelective(XsDdmx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    XsDdmx selectOneByExample(XsDdmxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    XsDdmx selectOneByExampleSelective(@Param("example") XsDdmxExample example, @Param("selective") XsDdmx.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    List<XsDdmx> selectByExampleSelective(@Param("example") XsDdmxExample example, @Param("selective") XsDdmx.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    List<XsDdmx> selectByExample(XsDdmxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    XsDdmx selectByPrimaryKeySelective(@Param("ddmxh") Long ddmxh, @Param("selective") XsDdmx.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    XsDdmx selectByPrimaryKey(Long ddmxh);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") XsDdmx record, @Param("example") XsDdmxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") XsDdmx record, @Param("example") XsDdmxExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(XsDdmx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xs_ddmx
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(XsDdmx record);
}