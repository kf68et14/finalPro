package com.vti.dto;

import com.vti.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {

	private int id;
	
	private String username;

	private String departmentName;

}
