package com.vti.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.vti.form.AccountRequestFormForCreate;
import com.vti.form.AccountRequestFormForUpdate;
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

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;

@RestController
	@RequestMapping(value = "api/v1/accounts")
public class AccountController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAccountService service;

	// Phần specification và filter sai
//		public ResponseEntity<List<AccountResponseDTO>>  getAllAccounts
//		(@RequestParam(value = "search", required = false) String search,
//				Pageable pageable, AccountFilterForm filterForm)
//			Page<Account> accounts = service.getAllAccounts(search, pageable, filterForm);
//		Page<Account> accounts = service.getAllAccounts(search, pageable, filterForm);
//		List<AccountResponseDTO> dtos = accounts
//			.stream()
//			.map(account -> modelMapper.map(account, AccountResponseDTO.class))
//			.collect(Collectors.toList());
//			return ResponseEntity<>(dtos, HttpStatus.OK);
	// OK roi
	@GetMapping()
	public ResponseEntity<Page<AccountResponseDTO>>  getAllAccounts(
			@RequestParam(value = "search", required = false) String search,
			Pageable pageable) {
		Page<Account> accounts = service.getAllAccounts(search, pageable);
		Page<AccountResponseDTO> result = accounts.map(account ->
			modelMapper.map(account, AccountResponseDTO.class));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// OK roi
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getAccountByID(@PathVariable(name = "id") int id) throws AccountNotFoundException {
		return new ResponseEntity<>(service.getAccountByID(id), HttpStatus.OK);
	}

	// Create account
	@PostMapping
	public ResponseEntity<?> createAccount(@RequestBody  AccountRequestFormForCreate form) {
		service.createAccount(form);
		return new ResponseEntity<>("Create successfully!", HttpStatus.OK);
	}



	// update by id
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable(name = "id") int id,
										   @RequestBody @Valid AccountRequestFormForUpdate form) {
		service.updateAccount(id, form);
		return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
	}

	// update partial information by id
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> updateAccountPartially (@PathVariable(name = "id") int id,
													@RequestBody Map<String, Object> fields){
		service.updateAccountPartially(id, fields);
		return new ResponseEntity<String>("update successfully", HttpStatus.OK);
	}

	// delete nhieu account
	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteAccounts(@PathVariable(name = "ids") List<Integer> ids) {
		service.deleteAccounts(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}

}
