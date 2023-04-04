package br.com.project.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QueryBuilder<T> {

    private String select = "";
    private String from = "";
    private String where = "";
    private final Map<String, Object> params = new HashMap<>();
    private final EntityManager entityManager;
    private final Calendar calendar = Calendar.getInstance();

    public QueryBuilder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public QueryBuilder<T> select(String strParam) {
        StringBuilder strQuery = new StringBuilder();

        if (Validator.stringIsValid(select)) {
            strQuery
                    .append(select)
                    .append(", ")
                    .append(strParam);
        } else {
            strQuery.append("SELECT ")
                    .append(strParam);
        }

        select = strQuery.toString();

        return this;
    }

    public QueryBuilder<T> from(String strParam) {
        StringBuilder strQuery = new StringBuilder();

        if (Validator.stringIsValid(from)) {
            strQuery.append(from)
                    .append(", ")
                    .append(strParam);
        } else {
            strQuery.append(" FROM ")
                    .append(strParam);
        }

        from = strQuery.toString();

        return this;
    }

    public QueryBuilder<T> where(String strParam) {
        StringBuilder strQuery = new StringBuilder();

        if (Validator.stringIsValid(where)) {
            strQuery.append(where)
                    .append(" AND ")
                    .append(strParam);
        } else {
            strQuery.append(" WHERE ")
                    .append(strParam);
        }

        where = strQuery.toString();

        return this;
    }

    public QueryBuilder<T> param(String paramName, Object paramValue) {
        params.put(paramName, paramValue);

        return this;
    }

    public QueryBuilder<T> paramLike(String paramName, Object paramValue) {
        params.put(paramName, "%" + paramValue + "%");

        return this;
    }

    public QueryBuilder<T> paramDate(String paramName, String date) throws ParseException {
        params.put(paramName, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()) + "%");

        return this;
    }

    public QueryBuilder<T> paramInitialDate(String paramName, Date initialDate) {
        calendar.setTime(initialDate);

        return setParamDates(paramName);
    }

    public QueryBuilder<T> paramFinalDate(String paramName, Date finalDate) {
        calendar.setTime(finalDate);
        calendar.add(Calendar.DATE, 1);

        return setParamDates(paramName);
    }

    private QueryBuilder<T> setParamDates(String paramName) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        params.put(paramName, calendar.getTime());

        return this;
    }

    public List<T> getAllResults() {
        return organizedQuery().getResultList();
    }

    public T getSingleResult() {
        return (T) organizedQuery().getSingleResult();
    }

    private Query organizedQuery() {
        Query query = entityManager.createQuery(select + from + where);

        if (!params.isEmpty()) {
            params.forEach(query::setParameter);
        }

        return query;
    }

    private String preservesTwoNumbersDefault(Integer value) {
        if (value < 10) {
            return "0" + value;
        }

        return value.toString();
    }

}
