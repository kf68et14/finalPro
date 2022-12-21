package com.vti.service;

import com.vti.dto.AccountResponseDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountRequestFormForCreate;
import com.vti.form.AccountRequestFormForUpdate;
import com.vti.repository.IAccountRepository;
import com.vti.specification.AccountSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.security.auth.login.AccountNotFoundException;
import javax.sql.RowSet;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AccountService implements IAccountService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private IAccountRepository repository;

	public Page<Account> getAllAccounts(String search, Pageable pageable, AccountFilterForm filterForm) {
		AccountSpecificationBuilder specification = new AccountSpecificationBuilder(search, filterForm);

		return repository.findAll(specification.build(), pageable);
	}

	public void updateAccountPartially(int id, Map<String, Object> fields) {
		Optional<Account> account = repository.findById(id);

		if (account.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Account.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, account.get(), value);
			});
		}

		repository.save(account.get());
	}

	public void updateAccount(int id, AccountRequestFormForUpdate form) {
		Optional<Account> account = repository.findById(id);

		if (account.isPresent()) {
			account.get().setUsername(form.getUsername());
			account.get().setFirstName(form.getFirstName());
			account.get().setLastName(form.getLastName());
			account.get().setRole(form.getRole());
			Department department = new Department();
			department.setId(form.getDepartmentId());
			account.get().setDepartment(department);
		}
		repository.save(account.get());
	}

	public void deleteAccounts(List<Integer> ids) {
		repository.deleteByIdIn(ids);
	}

	@Override
	public AccountResponseDTO getAccountByID(int id) {
		Optional<Account> account = repository.findById(id);
		if (account.isPresent()) {
			AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
			accountResponseDTO.setId(id);
			accountResponseDTO.setUsername(account.get().getUsername());
			accountResponseDTO.setDepartmentName(account.get().getDepartment().getName());
			return accountResponseDTO;
		}
		return null;
	}

	@Override
	public void createAccount(AccountRequestFormForCreate form) {
		Account account = new Account();
		account.setUsername(form.getUsername());
		account.setFirstName(form.getFirstName());
		account.setLastName(form.getLastName());
		account.setRole(form.getRole());

		Department department = new Department();
		department.setId(form.getDepartmentId());
		account.setDepartment(department);

		repository.save(account);
	}
}

