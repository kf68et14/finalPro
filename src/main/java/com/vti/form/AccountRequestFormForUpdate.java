package com.vti.form;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountRequestFormForUpdate {
    private String username;

    private String firstName;

    private String lastName;

    private Role role;

    private Department department;

    public Account toEntity(){
        return new Account(username, firstName, lastName, role, department);
    }
}
