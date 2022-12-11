//package com.vti.specification;
//
//import com.vti.entity.Account;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//public class CustomSpecification implements Specification<Account> {
//    private String field;
//
//    private Object value;
//
//    @SuppressWarnings("serial")
//    @Override
//    public Predicate toPredicate(
//            Root<Account> root,
//            CriteriaQuery<?> query,
//            CriteriaBuilder criteriaBuilder) {
//
//        if (field.equalsIgnoreCase("username")) {
//            return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
//        }
//
//        if (field.equalsIgnoreCase("firstName")) {
//            return criteriaBuilder.like(root.get("firstName"), "%" + value.toString() + "%");
//        }
//
//        if (field.equalsIgnoreCase("lastName")) {
//            return criteriaBuilder.like(root.get("lastName"), "%" + value.toString() + "%");
//        }
//
//        if (field.equalsIgnoreCase("name")) {
//            return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
//        }
//
//        return null;
//    }
//}
