package com.vti.form;

import com.vti.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilterForm {

	private String role;

	private String departmentName;
}

