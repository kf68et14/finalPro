package com.vti.specification;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.AccountFilterForm;
import com.vti.form.DepartmentFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class DepartmentSpecificationBuilder {
    private DepartmentFilterForm filter;
    private String search;

    public DepartmentSpecificationBuilder(DepartmentFilterForm filter, String search){
        this.filter = filter;
        this.search = search;
    }

    @SuppressWarnings("deprecation")
    public Specification<Department> build() {

        SearchCriteria searchCriteria = new SearchCriteria("name","Like", search);

        SearchCriteria minCreatedDateCriteria = new SearchCriteria("createdDate", ">=", filter.getMin_created_date());
        SearchCriteria maxCreatedDateCriteria = new SearchCriteria("createdDate", "<=", filter.getMax_created_date());

        Specification<Department> where = null;

        // search
        if (!StringUtils.isEmpty(search)) {
            where = new DepartmentSpecification(searchCriteria);
        }

        // filer by create Date
        if (filter.getMin_created_date() != 0){
            if(where != null){
                where = where.and(new DepartmentSpecification(minCreatedDateCriteria));
            } else {
                where = new DepartmentSpecification(minCreatedDateCriteria);
            }
        }

        if (filter.getMax_created_date() != 0){
            if(where != null){
                where = where.and(new DepartmentSpecification(maxCreatedDateCriteria));
            } else {
                where = new AccountSpecification(maxCreatedDateCriteria);
            }
        }

        return where;
    }

}
