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

        SearchCriteria searchCriteria1 = new SearchCriteria("role","Like", search);
        SearchCriteria searchCriteria2 = new SearchCriteria("department","Like",search);

        Specification<Account> where = null;

        // search
        if (!StringUtils.isEmpty(search)) {
            where = new AccountSpecification(searchCriteria1);
            where = where.and(searchCriteria2);
        }
        return where;
    }


}
