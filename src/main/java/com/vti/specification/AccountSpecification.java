package com.vti.specification;

import com.vti.entity.Account;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountSpecification implements Specification<Account> {

    private static final long serialVersionUID = 1L;
    private SearchCriteria criteria;

    public AccountSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(
            Root<Account> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder) {

        if (criteria.getOperator().equalsIgnoreCase("Like")) {
            return criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }

        if (criteria.getOperator().equalsIgnoreCase("Equals")) {
            return criteriaBuilder.equal(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
        return null;
    }
}
