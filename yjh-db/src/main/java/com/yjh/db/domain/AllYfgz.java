package com.yjh.db.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class AllYfgz {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_yfgz.yfgzbm
     *
     * @mbg.generated
     */
    private Integer yfgzbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_yfgz.yfgz
     *
     * @mbg.generated
     */
    private String yfgz;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_yfgz.sm
     *
     * @mbg.generated
     */
    private String sm;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_yfgz.yfgzbm
     *
     * @return the value of all_yfgz.yfgzbm
     *
     * @mbg.generated
     */
    public Integer getYfgzbm() {
        return yfgzbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_yfgz.yfgzbm
     *
     * @param yfgzbm the value for all_yfgz.yfgzbm
     *
     * @mbg.generated
     */
    public void setYfgzbm(Integer yfgzbm) {
        this.yfgzbm = yfgzbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_yfgz.yfgz
     *
     * @return the value of all_yfgz.yfgz
     *
     * @mbg.generated
     */
    public String getYfgz() {
        return yfgz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_yfgz.yfgz
     *
     * @param yfgz the value for all_yfgz.yfgz
     *
     * @mbg.generated
     */
    public void setYfgz(String yfgz) {
        this.yfgz = yfgz;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_yfgz.sm
     *
     * @return the value of all_yfgz.sm
     *
     * @mbg.generated
     */
    public String getSm() {
        return sm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_yfgz.sm
     *
     * @param sm the value for all_yfgz.sm
     *
     * @mbg.generated
     */
    public void setSm(String sm) {
        this.sm = sm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_yfgz
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", yfgzbm=").append(yfgzbm);
        sb.append(", yfgz=").append(yfgz);
        sb.append(", sm=").append(sm);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_yfgz
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
        AllYfgz other = (AllYfgz) that;
        return (this.getYfgzbm() == null ? other.getYfgzbm() == null : this.getYfgzbm().equals(other.getYfgzbm()))
            && (this.getYfgz() == null ? other.getYfgz() == null : this.getYfgz().equals(other.getYfgz()))
            && (this.getSm() == null ? other.getSm() == null : this.getSm().equals(other.getSm()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_yfgz
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getYfgzbm() == null) ? 0 : getYfgzbm().hashCode());
        result = prime * result + ((getYfgz() == null) ? 0 : getYfgz().hashCode());
        result = prime * result + ((getSm() == null) ? 0 : getSm().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table all_yfgz
     *
     * @mbg.generated
     */
    public enum Column {
        yfgzbm("yfgzbm", "yfgzbm", "INTEGER", false),
        yfgz("yfgz", "yfgz", "VARCHAR", false),
        sm("sm", "sm", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_yfgz
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
         * This method corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_yfgz
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
         * This method corresponds to the database table all_yfgz
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
         * This method corresponds to the database table all_yfgz
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}