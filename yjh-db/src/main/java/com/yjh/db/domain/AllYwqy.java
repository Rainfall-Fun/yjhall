package com.yjh.db.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class AllYwqy {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_ywqy.ywqybm
     *
     * @mbg.generated
     */
    private Integer ywqybm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_ywqy.qymc
     *
     * @mbg.generated
     */
    private String qymc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_ywqy.sjqybm
     *
     * @mbg.generated
     */
    private Integer sjqybm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_ywqy.qydj
     *
     * @mbg.generated
     */
    private Integer qydj;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_ywqy.bz
     *
     * @mbg.generated
     */
    private String bz;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_ywqy.ywqybm
     *
     * @return the value of all_ywqy.ywqybm
     *
     * @mbg.generated
     */
    public Integer getYwqybm() {
        return ywqybm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_ywqy.ywqybm
     *
     * @param ywqybm the value for all_ywqy.ywqybm
     *
     * @mbg.generated
     */
    public void setYwqybm(Integer ywqybm) {
        this.ywqybm = ywqybm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_ywqy.qymc
     *
     * @return the value of all_ywqy.qymc
     *
     * @mbg.generated
     */
    public String getQymc() {
        return qymc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_ywqy.qymc
     *
     * @param qymc the value for all_ywqy.qymc
     *
     * @mbg.generated
     */
    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_ywqy.sjqybm
     *
     * @return the value of all_ywqy.sjqybm
     *
     * @mbg.generated
     */
    public Integer getSjqybm() {
        return sjqybm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_ywqy.sjqybm
     *
     * @param sjqybm the value for all_ywqy.sjqybm
     *
     * @mbg.generated
     */
    public void setSjqybm(Integer sjqybm) {
        this.sjqybm = sjqybm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_ywqy.qydj
     *
     * @return the value of all_ywqy.qydj
     *
     * @mbg.generated
     */
    public Integer getQydj() {
        return qydj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_ywqy.qydj
     *
     * @param qydj the value for all_ywqy.qydj
     *
     * @mbg.generated
     */
    public void setQydj(Integer qydj) {
        this.qydj = qydj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_ywqy.bz
     *
     * @return the value of all_ywqy.bz
     *
     * @mbg.generated
     */
    public String getBz() {
        return bz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_ywqy.bz
     *
     * @param bz the value for all_ywqy.bz
     *
     * @mbg.generated
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ywqybm=").append(ywqybm);
        sb.append(", qymc=").append(qymc);
        sb.append(", sjqybm=").append(sjqybm);
        sb.append(", qydj=").append(qydj);
        sb.append(", bz=").append(bz);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AllYwqy other = (AllYwqy) that;
        return (this.getYwqybm() == null ? other.getYwqybm() == null : this.getYwqybm().equals(other.getYwqybm()))
            && (this.getQymc() == null ? other.getQymc() == null : this.getQymc().equals(other.getQymc()))
            && (this.getSjqybm() == null ? other.getSjqybm() == null : this.getSjqybm().equals(other.getSjqybm()))
            && (this.getQydj() == null ? other.getQydj() == null : this.getQydj().equals(other.getQydj()))
            && (this.getBz() == null ? other.getBz() == null : this.getBz().equals(other.getBz()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getYwqybm() == null) ? 0 : getYwqybm().hashCode());
        result = prime * result + ((getQymc() == null) ? 0 : getQymc().hashCode());
        result = prime * result + ((getSjqybm() == null) ? 0 : getSjqybm().hashCode());
        result = prime * result + ((getQydj() == null) ? 0 : getQydj().hashCode());
        result = prime * result + ((getBz() == null) ? 0 : getBz().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table all_ywqy
     *
     * @mbg.generated
     */
    public enum Column {
        ywqybm("ywqybm", "ywqybm", "INTEGER", false),
        qymc("qymc", "qymc", "VARCHAR", false),
        sjqybm("sjqybm", "sjqybm", "INTEGER", false),
        qydj("qydj", "qydj", "INTEGER", false),
        bz("bz", "bz", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_ywqy
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}