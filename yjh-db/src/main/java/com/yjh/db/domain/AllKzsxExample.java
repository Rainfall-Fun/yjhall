package com.yjh.db.domain;

import java.util.ArrayList;
import java.util.List;

public class AllKzsxExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public AllKzsxExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public AllKzsxExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public AllKzsxExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public static Criteria newAndCreateCriteria() {
        AllKzsxExample example = new AllKzsxExample();
        return example.createCriteria();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public AllKzsxExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public AllKzsxExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andKzsxbmIsNull() {
            addCriterion("kzsxbm is null");
            return (Criteria) this;
        }

        public Criteria andKzsxbmIsNotNull() {
            addCriterion("kzsxbm is not null");
            return (Criteria) this;
        }

        public Criteria andKzsxbmEqualTo(Integer value) {
            addCriterion("kzsxbm =", value, "kzsxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKzsxbmEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kzsxbm = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKzsxbmNotEqualTo(Integer value) {
            addCriterion("kzsxbm <>", value, "kzsxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKzsxbmNotEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kzsxbm <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKzsxbmGreaterThan(Integer value) {
            addCriterion("kzsxbm >", value, "kzsxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKzsxbmGreaterThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kzsxbm > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKzsxbmGreaterThanOrEqualTo(Integer value) {
            addCriterion("kzsxbm >=", value, "kzsxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKzsxbmGreaterThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kzsxbm >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKzsxbmLessThan(Integer value) {
            addCriterion("kzsxbm <", value, "kzsxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKzsxbmLessThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kzsxbm < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKzsxbmLessThanOrEqualTo(Integer value) {
            addCriterion("kzsxbm <=", value, "kzsxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKzsxbmLessThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kzsxbm <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKzsxbmIn(List<Integer> values) {
            addCriterion("kzsxbm in", values, "kzsxbm");
            return (Criteria) this;
        }

        public Criteria andKzsxbmNotIn(List<Integer> values) {
            addCriterion("kzsxbm not in", values, "kzsxbm");
            return (Criteria) this;
        }

        public Criteria andKzsxbmBetween(Integer value1, Integer value2) {
            addCriterion("kzsxbm between", value1, value2, "kzsxbm");
            return (Criteria) this;
        }

        public Criteria andKzsxbmNotBetween(Integer value1, Integer value2) {
            addCriterion("kzsxbm not between", value1, value2, "kzsxbm");
            return (Criteria) this;
        }

        public Criteria andSxmcIsNull() {
            addCriterion("sxmc is null");
            return (Criteria) this;
        }

        public Criteria andSxmcIsNotNull() {
            addCriterion("sxmc is not null");
            return (Criteria) this;
        }

        public Criteria andSxmcEqualTo(String value) {
            addCriterion("sxmc =", value, "sxmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxmcEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxmc = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxmcNotEqualTo(String value) {
            addCriterion("sxmc <>", value, "sxmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxmcNotEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxmc <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxmcGreaterThan(String value) {
            addCriterion("sxmc >", value, "sxmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxmcGreaterThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxmc > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxmcGreaterThanOrEqualTo(String value) {
            addCriterion("sxmc >=", value, "sxmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxmcGreaterThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxmc >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxmcLessThan(String value) {
            addCriterion("sxmc <", value, "sxmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxmcLessThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxmc < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxmcLessThanOrEqualTo(String value) {
            addCriterion("sxmc <=", value, "sxmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxmcLessThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxmc <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxmcLike(String value) {
            addCriterion("sxmc like", value, "sxmc");
            return (Criteria) this;
        }

        public Criteria andSxmcNotLike(String value) {
            addCriterion("sxmc not like", value, "sxmc");
            return (Criteria) this;
        }

        public Criteria andSxmcIn(List<String> values) {
            addCriterion("sxmc in", values, "sxmc");
            return (Criteria) this;
        }

        public Criteria andSxmcNotIn(List<String> values) {
            addCriterion("sxmc not in", values, "sxmc");
            return (Criteria) this;
        }

        public Criteria andSxmcBetween(String value1, String value2) {
            addCriterion("sxmc between", value1, value2, "sxmc");
            return (Criteria) this;
        }

        public Criteria andSxmcNotBetween(String value1, String value2) {
            addCriterion("sxmc not between", value1, value2, "sxmc");
            return (Criteria) this;
        }

        public Criteria andSxsjlxIsNull() {
            addCriterion("sxsjlx is null");
            return (Criteria) this;
        }

        public Criteria andSxsjlxIsNotNull() {
            addCriterion("sxsjlx is not null");
            return (Criteria) this;
        }

        public Criteria andSxsjlxEqualTo(Integer value) {
            addCriterion("sxsjlx =", value, "sxsjlx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxsjlxEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxsjlx = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxsjlxNotEqualTo(Integer value) {
            addCriterion("sxsjlx <>", value, "sxsjlx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxsjlxNotEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxsjlx <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxsjlxGreaterThan(Integer value) {
            addCriterion("sxsjlx >", value, "sxsjlx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxsjlxGreaterThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxsjlx > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxsjlxGreaterThanOrEqualTo(Integer value) {
            addCriterion("sxsjlx >=", value, "sxsjlx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxsjlxGreaterThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxsjlx >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxsjlxLessThan(Integer value) {
            addCriterion("sxsjlx <", value, "sxsjlx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxsjlxLessThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxsjlx < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxsjlxLessThanOrEqualTo(Integer value) {
            addCriterion("sxsjlx <=", value, "sxsjlx");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andSxsjlxLessThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("sxsjlx <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSxsjlxIn(List<Integer> values) {
            addCriterion("sxsjlx in", values, "sxsjlx");
            return (Criteria) this;
        }

        public Criteria andSxsjlxNotIn(List<Integer> values) {
            addCriterion("sxsjlx not in", values, "sxsjlx");
            return (Criteria) this;
        }

        public Criteria andSxsjlxBetween(Integer value1, Integer value2) {
            addCriterion("sxsjlx between", value1, value2, "sxsjlx");
            return (Criteria) this;
        }

        public Criteria andSxsjlxNotBetween(Integer value1, Integer value2) {
            addCriterion("sxsjlx not between", value1, value2, "sxsjlx");
            return (Criteria) this;
        }

        public Criteria andKdIsNull() {
            addCriterion("kd is null");
            return (Criteria) this;
        }

        public Criteria andKdIsNotNull() {
            addCriterion("kd is not null");
            return (Criteria) this;
        }

        public Criteria andKdEqualTo(Integer value) {
            addCriterion("kd =", value, "kd");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKdEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kd = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKdNotEqualTo(Integer value) {
            addCriterion("kd <>", value, "kd");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKdNotEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kd <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKdGreaterThan(Integer value) {
            addCriterion("kd >", value, "kd");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKdGreaterThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kd > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKdGreaterThanOrEqualTo(Integer value) {
            addCriterion("kd >=", value, "kd");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKdGreaterThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kd >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKdLessThan(Integer value) {
            addCriterion("kd <", value, "kd");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKdLessThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kd < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKdLessThanOrEqualTo(Integer value) {
            addCriterion("kd <=", value, "kd");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andKdLessThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("kd <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andKdIn(List<Integer> values) {
            addCriterion("kd in", values, "kd");
            return (Criteria) this;
        }

        public Criteria andKdNotIn(List<Integer> values) {
            addCriterion("kd not in", values, "kd");
            return (Criteria) this;
        }

        public Criteria andKdBetween(Integer value1, Integer value2) {
            addCriterion("kd between", value1, value2, "kd");
            return (Criteria) this;
        }

        public Criteria andKdNotBetween(Integer value1, Integer value2) {
            addCriterion("kd not between", value1, value2, "kd");
            return (Criteria) this;
        }

        public Criteria andXswsIsNull() {
            addCriterion("xsws is null");
            return (Criteria) this;
        }

        public Criteria andXswsIsNotNull() {
            addCriterion("xsws is not null");
            return (Criteria) this;
        }

        public Criteria andXswsEqualTo(Integer value) {
            addCriterion("xsws =", value, "xsws");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andXswsEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("xsws = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXswsNotEqualTo(Integer value) {
            addCriterion("xsws <>", value, "xsws");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andXswsNotEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("xsws <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXswsGreaterThan(Integer value) {
            addCriterion("xsws >", value, "xsws");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andXswsGreaterThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("xsws > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXswsGreaterThanOrEqualTo(Integer value) {
            addCriterion("xsws >=", value, "xsws");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andXswsGreaterThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("xsws >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXswsLessThan(Integer value) {
            addCriterion("xsws <", value, "xsws");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andXswsLessThanColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("xsws < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXswsLessThanOrEqualTo(Integer value) {
            addCriterion("xsws <=", value, "xsws");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria andXswsLessThanOrEqualToColumn(AllKzsx.Column column) {
            addCriterion(new StringBuilder("xsws <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andXswsIn(List<Integer> values) {
            addCriterion("xsws in", values, "xsws");
            return (Criteria) this;
        }

        public Criteria andXswsNotIn(List<Integer> values) {
            addCriterion("xsws not in", values, "xsws");
            return (Criteria) this;
        }

        public Criteria andXswsBetween(Integer value1, Integer value2) {
            addCriterion("xsws between", value1, value2, "xsws");
            return (Criteria) this;
        }

        public Criteria andXswsNotBetween(Integer value1, Integer value2) {
            addCriterion("xsws not between", value1, value2, "xsws");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table all_kzsx
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        private AllKzsxExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        protected Criteria(AllKzsxExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public AllKzsxExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        @Deprecated
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            } else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {
            /**
             * This method was generated by MyBatis Generator.
             * This method corresponds to the database table all_kzsx
             *
             * @mbg.generated
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table all_kzsx
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public interface ICriteriaWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_kzsx
         *
         * @mbg.generated
         */
        void example(com.yjh.db.domain.AllKzsxExample example);
    }
}