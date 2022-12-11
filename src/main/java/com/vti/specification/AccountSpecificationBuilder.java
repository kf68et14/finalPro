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

        SearchCriteria searchName = new SearchCriteria("username","Like", search);
        SearchCriteria searchFirstName = new SearchCriteria("firstname","Like", search);
        SearchCriteria searchLastName = new SearchCriteria("lastname","Like", search);

        SearchCriteria getRoleCriteria = new SearchCriteria("role", "Equals", filter.getRole());
        SearchCriteria getDepartmentCriteria = new SearchCriteria("department", "Equals", filter.getDepartment());


        Specification<Account> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = new AccountSpecification(searchName);
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = where.and(new AccountSpecification(searchFirstName));
        }

        if (!StringUtils.isEmpty(search)) {
            search = search.trim();
            where = where.and(new AccountSpecification(searchLastName));
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
