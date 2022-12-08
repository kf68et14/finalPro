package com.vti.form;

import com.vti.entity.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentRequestFormForUpdate {
    private String name;

    private Department.Type type;

}
