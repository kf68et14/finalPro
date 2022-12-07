package com.vti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountResponseDTO {

	private int id;
	
	private String username;

	private String departmentName;
}
