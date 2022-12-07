package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vti.dto.AccountResponseDTO;
import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/accounts")
public class AccountController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAccountService service;

	@GetMapping()
	public ResponseEntity<List<AccountResponseDTO>>  getAllAccounts(
			@RequestParam(value = "search", required = false) String search,
			Pageable pageable,
			AccountFilterForm filterForm) {
		Page<Account> accounts = service.getAllAccounts(search, pageable, filterForm);
		
		List<AccountResponseDTO> dtos = modelMapper.map(accounts, new TypeToken<List<AccountResponseDTO>>() {}.getType());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping("/account")
	public ResponseEntity

}
