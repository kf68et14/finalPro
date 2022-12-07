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
            CustomSpecification role = new CustomSpecification("role", search);
            where = Specification.where(role);
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomSpecification department = new CustomSpecification("department", search);
            where = where.or(department);
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

        if (field.equalsIgnoreCase("role")) {
            return criteriaBuilder.like(root.get("role"), "%" + value.toString() + "%");
        }

        if (field.equalsIgnoreCase("department")) {
            return criteriaBuilder.like(root.get("department"), "%" + value.toString() + "%");
        }
        return null;
    }
}
