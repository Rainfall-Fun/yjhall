package com.yjh.db.domain;

import java.util.ArrayList;
import java.util.List;

public class HyClExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public HyClExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
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
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public HyClExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public HyClExample orderBy(String ... orderByClauses) {
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
     * This method corresponds to the database table hy_cl
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
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
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
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public static Criteria newAndCreateCriteria() {
        HyClExample example = new HyClExample();
        return example.createCriteria();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public HyClExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hy_cl
     *
     * @mbg.generated
     */
    public HyClExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hy_cl
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

        public Criteria andHyrzclbmIsNull() {
            addCriterion("hyrzclbm is null");
            return (Criteria) this;
        }

        public Criteria andHyrzclbmIsNotNull() {
            addCriterion("hyrzclbm is not null");
            return (Criteria) this;
        }

        public Criteria andHyrzclbmEqualTo(Long value) {
            addCriterion("hyrzclbm =", value, "hyrzclbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHyrzclbmEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hyrzclbm = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHyrzclbmNotEqualTo(Long value) {
            addCriterion("hyrzclbm <>", value, "hyrzclbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHyrzclbmNotEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hyrzclbm <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHyrzclbmGreaterThan(Long value) {
            addCriterion("hyrzclbm >", value, "hyrzclbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHyrzclbmGreaterThanColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hyrzclbm > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHyrzclbmGreaterThanOrEqualTo(Long value) {
            addCriterion("hyrzclbm >=", value, "hyrzclbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHyrzclbmGreaterThanOrEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hyrzclbm >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHyrzclbmLessThan(Long value) {
            addCriterion("hyrzclbm <", value, "hyrzclbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHyrzclbmLessThanColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hyrzclbm < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHyrzclbmLessThanOrEqualTo(Long value) {
            addCriterion("hyrzclbm <=", value, "hyrzclbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHyrzclbmLessThanOrEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hyrzclbm <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHyrzclbmIn(List<Long> values) {
            addCriterion("hyrzclbm in", values, "hyrzclbm");
            return (Criteria) this;
        }

        public Criteria andHyrzclbmNotIn(List<Long> values) {
            addCriterion("hyrzclbm not in", values, "hyrzclbm");
            return (Criteria) this;
        }

        public Criteria andHyrzclbmBetween(Long value1, Long value2) {
            addCriterion("hyrzclbm between", value1, value2, "hyrzclbm");
            return (Criteria) this;
        }

        public Criteria andHyrzclbmNotBetween(Long value1, Long value2) {
            addCriterion("hyrzclbm not between", value1, value2, "hyrzclbm");
            return (Criteria) this;
        }

        public Criteria andHybhIsNull() {
            addCriterion("hybh is null");
            return (Criteria) this;
        }

        public Criteria andHybhIsNotNull() {
            addCriterion("hybh is not null");
            return (Criteria) this;
        }

        public Criteria andHybhEqualTo(String value) {
            addCriterion("hybh =", value, "hybh");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHybhEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hybh = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHybhNotEqualTo(String value) {
            addCriterion("hybh <>", value, "hybh");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHybhNotEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hybh <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHybhGreaterThan(String value) {
            addCriterion("hybh >", value, "hybh");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHybhGreaterThanColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hybh > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHybhGreaterThanOrEqualTo(String value) {
            addCriterion("hybh >=", value, "hybh");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHybhGreaterThanOrEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hybh >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHybhLessThan(String value) {
            addCriterion("hybh <", value, "hybh");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHybhLessThanColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hybh < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHybhLessThanOrEqualTo(String value) {
            addCriterion("hybh <=", value, "hybh");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHybhLessThanOrEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hybh <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHybhLike(String value) {
            addCriterion("hybh like", value, "hybh");
            return (Criteria) this;
        }

        public Criteria andHybhNotLike(String value) {
            addCriterion("hybh not like", value, "hybh");
            return (Criteria) this;
        }

        public Criteria andHybhIn(List<String> values) {
            addCriterion("hybh in", values, "hybh");
            return (Criteria) this;
        }

        public Criteria andHybhNotIn(List<String> values) {
            addCriterion("hybh not in", values, "hybh");
            return (Criteria) this;
        }

        public Criteria andHybhBetween(String value1, String value2) {
            addCriterion("hybh between", value1, value2, "hybh");
            return (Criteria) this;
        }

        public Criteria andHybhNotBetween(String value1, String value2) {
            addCriterion("hybh not between", value1, value2, "hybh");
            return (Criteria) this;
        }

        public Criteria andHycllxbmIsNull() {
            addCriterion("hycllxbm is null");
            return (Criteria) this;
        }

        public Criteria andHycllxbmIsNotNull() {
            addCriterion("hycllxbm is not null");
            return (Criteria) this;
        }

        public Criteria andHycllxbmEqualTo(Integer value) {
            addCriterion("hycllxbm =", value, "hycllxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHycllxbmEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hycllxbm = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHycllxbmNotEqualTo(Integer value) {
            addCriterion("hycllxbm <>", value, "hycllxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHycllxbmNotEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hycllxbm <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHycllxbmGreaterThan(Integer value) {
            addCriterion("hycllxbm >", value, "hycllxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHycllxbmGreaterThanColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hycllxbm > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHycllxbmGreaterThanOrEqualTo(Integer value) {
            addCriterion("hycllxbm >=", value, "hycllxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHycllxbmGreaterThanOrEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hycllxbm >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHycllxbmLessThan(Integer value) {
            addCriterion("hycllxbm <", value, "hycllxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHycllxbmLessThanColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hycllxbm < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHycllxbmLessThanOrEqualTo(Integer value) {
            addCriterion("hycllxbm <=", value, "hycllxbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andHycllxbmLessThanOrEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("hycllxbm <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHycllxbmIn(List<Integer> values) {
            addCriterion("hycllxbm in", values, "hycllxbm");
            return (Criteria) this;
        }

        public Criteria andHycllxbmNotIn(List<Integer> values) {
            addCriterion("hycllxbm not in", values, "hycllxbm");
            return (Criteria) this;
        }

        public Criteria andHycllxbmBetween(Integer value1, Integer value2) {
            addCriterion("hycllxbm between", value1, value2, "hycllxbm");
            return (Criteria) this;
        }

        public Criteria andHycllxbmNotBetween(Integer value1, Integer value2) {
            addCriterion("hycllxbm not between", value1, value2, "hycllxbm");
            return (Criteria) this;
        }

        public Criteria andClmcIsNull() {
            addCriterion("clmc is null");
            return (Criteria) this;
        }

        public Criteria andClmcIsNotNull() {
            addCriterion("clmc is not null");
            return (Criteria) this;
        }

        public Criteria andClmcEqualTo(String value) {
            addCriterion("clmc =", value, "clmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andClmcEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("clmc = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClmcNotEqualTo(String value) {
            addCriterion("clmc <>", value, "clmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andClmcNotEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("clmc <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClmcGreaterThan(String value) {
            addCriterion("clmc >", value, "clmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andClmcGreaterThanColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("clmc > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClmcGreaterThanOrEqualTo(String value) {
            addCriterion("clmc >=", value, "clmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andClmcGreaterThanOrEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("clmc >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClmcLessThan(String value) {
            addCriterion("clmc <", value, "clmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andClmcLessThanColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("clmc < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClmcLessThanOrEqualTo(String value) {
            addCriterion("clmc <=", value, "clmc");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public Criteria andClmcLessThanOrEqualToColumn(HyCl.Column column) {
            addCriterion(new StringBuilder("clmc <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andClmcLike(String value) {
            addCriterion("clmc like", value, "clmc");
            return (Criteria) this;
        }

        public Criteria andClmcNotLike(String value) {
            addCriterion("clmc not like", value, "clmc");
            return (Criteria) this;
        }

        public Criteria andClmcIn(List<String> values) {
            addCriterion("clmc in", values, "clmc");
            return (Criteria) this;
        }

        public Criteria andClmcNotIn(List<String> values) {
            addCriterion("clmc not in", values, "clmc");
            return (Criteria) this;
        }

        public Criteria andClmcBetween(String value1, String value2) {
            addCriterion("clmc between", value1, value2, "clmc");
            return (Criteria) this;
        }

        public Criteria andClmcNotBetween(String value1, String value2) {
            addCriterion("clmc not between", value1, value2, "clmc");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hy_cl
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        private HyClExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        protected Criteria(HyClExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        public HyClExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
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
         * This method corresponds to the database table hy_cl
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
         * This method corresponds to the database table hy_cl
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
             * This method corresponds to the database table hy_cl
             *
             * @mbg.generated
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hy_cl
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
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hy_cl
         *
         * @mbg.generated
         */
        void example(com.yjh.db.domain.HyClExample example);
    }
}