package com.vti.form;

import com.vti.entity.Account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AccountRequestFormForCreate {
    @NotBlank(message = "username must not be null")
    private String username;

    @NotBlank(message = "firstname must not be null")
    private String firstName;

    @NotBlank(message = "lastname must not be null")
    private String lastName;

    @Pattern(regexp = "USER|MANAGER|ADMIN", message = "the role must be USER, MANAGER or ADMIN")
    private Account.Role role;

    public Account toEntity(){
        return new Account(username, firstName, lastName, role);
    }



}
