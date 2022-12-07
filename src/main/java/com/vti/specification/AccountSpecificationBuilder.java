package com.vti.specification;

import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


public class AccountSpecificationBuilder {
    private AccountFilterForm filter;

    private String search;

    public AccountSpecificationBuilder(AccountFilterForm filter, String search){
        this.filter = filter;
        this.search = search;
    }

    @SuppressWarnings("deprecation")
    public Specification<Account> build() {

        SearchCriteria searchCriteria = new SearchCriteria("username","Like", search);

        SearchCriteria getRoleCriteria = new SearchCriteria("role", "Equals", filter.getRole());
        SearchCriteria getDepartmentCriteria = new SearchCriteria("department", "Equals", filter.getDepartment());


        Specification<Account> where = null;

        // search
        if (!StringUtils.isEmpty(search)) {
            where = new AccountSpecification(searchCriteria);
        }

        // filer by role or department
        if (filter.getRole() != null){
            if(where != null){
                where = where.and(new AccountSpecification(getRoleCriteria));
            } else {
                where = new AccountSpecification(getRoleCriteria);
            }
        }

        if (filter.getDepartment() != null){
            if(where != null){
                where = where.and(new AccountSpecification(getDepartmentCriteria));
            } else {
                where = new AccountSpecification(getDepartmentCriteria);
            }
        }

        return where;
    }


}
