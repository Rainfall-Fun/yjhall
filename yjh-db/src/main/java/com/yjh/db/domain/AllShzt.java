package com.yjh.db.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class AllShzt {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_shzt.shztbm
     *
     * @mbg.generated
     */
    private Integer shztbm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column all_shzt.shzt
     *
     * @mbg.generated
     */
    private String shzt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_shzt.shztbm
     *
     * @return the value of all_shzt.shztbm
     *
     * @mbg.generated
     */
    public Integer getShztbm() {
        return shztbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_shzt.shztbm
     *
     * @param shztbm the value for all_shzt.shztbm
     *
     * @mbg.generated
     */
    public void setShztbm(Integer shztbm) {
        this.shztbm = shztbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column all_shzt.shzt
     *
     * @return the value of all_shzt.shzt
     *
     * @mbg.generated
     */
    public String getShzt() {
        return shzt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column all_shzt.shzt
     *
     * @param shzt the value for all_shzt.shzt
     *
     * @mbg.generated
     */
    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shztbm=").append(shztbm);
        sb.append(", shzt=").append(shzt);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
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
        AllShzt other = (AllShzt) that;
        return (this.getShztbm() == null ? other.getShztbm() == null : this.getShztbm().equals(other.getShztbm()))
            && (this.getShzt() == null ? other.getShzt() == null : this.getShzt().equals(other.getShzt()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getShztbm() == null) ? 0 : getShztbm().hashCode());
        result = prime * result + ((getShzt() == null) ? 0 : getShzt().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table all_shzt
     *
     * @mbg.generated
     */
    public enum Column {
        shztbm("shztbm", "shztbm", "INTEGER", false),
        shzt("shzt", "shzt", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_shzt
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
         * This method corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_shzt
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
         * This method corresponds to the database table all_shzt
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
         * This method corresponds to the database table all_shzt
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}