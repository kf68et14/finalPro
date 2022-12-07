package com.vti.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class AccountSpecification implements Specification<Account> {

    private static final long serialVersionUID = 1L;
    @SuppressWarnings("deprecation")
    public static Specification<Account> buildWhere(String search, AccountFilterForm filterForm) {

        Specification<Account> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomSpecification username = new CustomSpecification("username", search);
            where = Specification.where(username);
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomSpecification firstName = new CustomSpecification("firstName", search);
            where = where.and(firstName);
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomSpecification lastName = new CustomSpecification("lastName", search);
            where = where.and(lastName);
        }
        return where;
    }
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Account> {

    @NonNull
    private String field;
    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(
            Root<Account> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {

        if (field.equalsIgnoreCase("username")) {
            return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
        }

        if (field.equalsIgnoreCase("firstName")) {
            return criteriaBuilder.like(root.get("firstName"), "%" + value.toString() + "%");
        }

        if (field.equalsIgnoreCase("lastName")) {
            return criteriaBuilder.like(root.get("lastName"), "%" + value.toString() + "%");
        }

        return null;
    }
}
