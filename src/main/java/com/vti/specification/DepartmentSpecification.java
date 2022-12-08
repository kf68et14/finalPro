package com.vti.specification;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.AccountFilterForm;
import com.vti.form.DepartmentFilterForm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DepartmentSpecification implements Specification<Department> {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("deprecation")
    public static Specification<Department> buildWhere(String search, DepartmentFilterForm filterForm) {

        Specification<Department> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            CustomSpecification name = new CustomSpecification("name", search);
            where = Specification.where(name);
        }
        return where;
    }
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Department> {

    @NonNull
    private String field;
    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(
            Root<Account> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {

        if (field.equalsIgnoreCase("name")) {
            return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
        }
        return null;
    }
}

