package com.vti.form;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class AccountRequestFormForUpdate {
    //@NotBlank(message = "username must not be null")
    private String username;

    //@NotBlank(message = "firstname must not be null")
    private String firstName;

    //@NotBlank(message = "lastname must not be null")
    private String lastName;

   // @Pattern(regexp = "USER|MANAGER|ADMIN", message = "the role must be USER, MANAGER or ADMIN")
    private Role role;

    private int departmentId;

//    public Account toEntity(){
//        return new Account(username, firstName, lastName, role, department);
//    }
}
