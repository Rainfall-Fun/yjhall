package com.yjh.db.domain;

import java.util.ArrayList;
import java.util.List;

public class AllSpssbkExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public AllSpssbkExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
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
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public AllSpssbkExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public AllSpssbkExample orderBy(String ... orderByClauses) {
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
     * This method corresponds to the database table all_spssbk
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
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
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
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public static Criteria newAndCreateCriteria() {
        AllSpssbkExample example = new AllSpssbkExample();
        return example.createCriteria();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public AllSpssbkExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table all_spssbk
     *
     * @mbg.generated
     */
    public AllSpssbkExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table all_spssbk
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

        public Criteria andSsbkbmIsNull() {
            addCriterion("ssbkbm is null");
            return (Criteria) this;
        }

        public Criteria andSsbkbmIsNotNull() {
            addCriterion("ssbkbm is not null");
            return (Criteria) this;
        }

        public Criteria andSsbkbmEqualTo(Integer value) {
            addCriterion("ssbkbm =", value, "ssbkbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkbmEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbkbm = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkbmNotEqualTo(Integer value) {
            addCriterion("ssbkbm <>", value, "ssbkbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkbmNotEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbkbm <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkbmGreaterThan(Integer value) {
            addCriterion("ssbkbm >", value, "ssbkbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkbmGreaterThanColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbkbm > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkbmGreaterThanOrEqualTo(Integer value) {
            addCriterion("ssbkbm >=", value, "ssbkbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkbmGreaterThanOrEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbkbm >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkbmLessThan(Integer value) {
            addCriterion("ssbkbm <", value, "ssbkbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkbmLessThanColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbkbm < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkbmLessThanOrEqualTo(Integer value) {
            addCriterion("ssbkbm <=", value, "ssbkbm");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkbmLessThanOrEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbkbm <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkbmIn(List<Integer> values) {
            addCriterion("ssbkbm in", values, "ssbkbm");
            return (Criteria) this;
        }

        public Criteria andSsbkbmNotIn(List<Integer> values) {
            addCriterion("ssbkbm not in", values, "ssbkbm");
            return (Criteria) this;
        }

        public Criteria andSsbkbmBetween(Integer value1, Integer value2) {
            addCriterion("ssbkbm between", value1, value2, "ssbkbm");
            return (Criteria) this;
        }

        public Criteria andSsbkbmNotBetween(Integer value1, Integer value2) {
            addCriterion("ssbkbm not between", value1, value2, "ssbkbm");
            return (Criteria) this;
        }

        public Criteria andSsbkIsNull() {
            addCriterion("ssbk is null");
            return (Criteria) this;
        }

        public Criteria andSsbkIsNotNull() {
            addCriterion("ssbk is not null");
            return (Criteria) this;
        }

        public Criteria andSsbkEqualTo(String value) {
            addCriterion("ssbk =", value, "ssbk");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbk = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkNotEqualTo(String value) {
            addCriterion("ssbk <>", value, "ssbk");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkNotEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbk <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkGreaterThan(String value) {
            addCriterion("ssbk >", value, "ssbk");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkGreaterThanColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbk > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkGreaterThanOrEqualTo(String value) {
            addCriterion("ssbk >=", value, "ssbk");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkGreaterThanOrEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbk >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkLessThan(String value) {
            addCriterion("ssbk <", value, "ssbk");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkLessThanColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbk < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkLessThanOrEqualTo(String value) {
            addCriterion("ssbk <=", value, "ssbk");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andSsbkLessThanOrEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("ssbk <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSsbkLike(String value) {
            addCriterion("ssbk like", value, "ssbk");
            return (Criteria) this;
        }

        public Criteria andSsbkNotLike(String value) {
            addCriterion("ssbk not like", value, "ssbk");
            return (Criteria) this;
        }

        public Criteria andSsbkIn(List<String> values) {
            addCriterion("ssbk in", values, "ssbk");
            return (Criteria) this;
        }

        public Criteria andSsbkNotIn(List<String> values) {
            addCriterion("ssbk not in", values, "ssbk");
            return (Criteria) this;
        }

        public Criteria andSsbkBetween(String value1, String value2) {
            addCriterion("ssbk between", value1, value2, "ssbk");
            return (Criteria) this;
        }

        public Criteria andSsbkNotBetween(String value1, String value2) {
            addCriterion("ssbk not between", value1, value2, "ssbk");
            return (Criteria) this;
        }

        public Criteria andBzIsNull() {
            addCriterion("bz is null");
            return (Criteria) this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("bz is not null");
            return (Criteria) this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("bz =", value, "bz");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andBzEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("bz = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("bz <>", value, "bz");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andBzNotEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("bz <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("bz >", value, "bz");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andBzGreaterThanColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("bz > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("bz >=", value, "bz");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andBzGreaterThanOrEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("bz >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("bz <", value, "bz");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andBzLessThanColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("bz < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("bz <=", value, "bz");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public Criteria andBzLessThanOrEqualToColumn(AllSpssbk.Column column) {
            addCriterion(new StringBuilder("bz <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBzLike(String value) {
            addCriterion("bz like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotLike(String value) {
            addCriterion("bz not like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzIn(List<String> values) {
            addCriterion("bz in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotIn(List<String> values) {
            addCriterion("bz not in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("bz between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("bz not between", value1, value2, "bz");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table all_spssbk
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        private AllSpssbkExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        protected Criteria(AllSpssbkExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        public AllSpssbkExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
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
         * This method corresponds to the database table all_spssbk
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
         * This method corresponds to the database table all_spssbk
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
             * This method corresponds to the database table all_spssbk
             *
             * @mbg.generated
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table all_spssbk
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
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table all_spssbk
         *
         * @mbg.generated
         */
        void example(com.yjh.db.domain.AllSpssbkExample example);
    }
}