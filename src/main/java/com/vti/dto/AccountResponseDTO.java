package com.vti.dto;

import com.vti.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountResponseDTO {

	private int id;
	
	private String username;

	private String departmentName;

	private Role role;
}
