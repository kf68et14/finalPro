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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.security.auth.login.AccountNotFoundException;
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

	public void updateAccountPartially(int id, Map<String, Object> fields) throws AccountNotFoundException {
		Optional<Account> account = repository.findById(id);
		if (!isAccountExitById(id)){
			throw new AccountNotFoundException("account not found");
		} else {
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

	public void deleteAccounts(List<Integer> ids) throws AccountNotFoundException {
		ids.forEach(id -> {
			if(isAccountExitById(id))
				try {
					throw new AccountNotFoundException("Account not found");
				} catch (AccountNotFoundException e) {
					throw new RuntimeException(e);
				}
		});
		repository.deleteByIdIn(ids);
	}

	@Override
	public AccountResponseDTO getAccountByID(int id) throws AccountNotFoundException {
		if (isAccountExitById(id)){
			Optional<Account> account = repository.findById(id);
			AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
			accountResponseDTO.setId(id);
			accountResponseDTO.setUsername(account.get().getUsername());
			accountResponseDTO.setDepartmentName(account.get().getDepartment().getName());
			return accountResponseDTO;
			}
		else {
			throw new AccountNotFoundException("account not exist");
		}
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

	@Override
	public void deleteById(int id) throws AccountNotFoundException {
		if(isAccountExitById(id)){
			repository.deleteById(id);

		}
		else {
			throw new AccountNotFoundException("account not exist");
		}
	}

	@Override
	public boolean isAccountExitById(int id) {
		Optional<Account> account = repository.findById(id);
		return account.isPresent();
	}


//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Account account = repository.findByUsername(username);
//		if (account == null){
//			throw new UsernameNotFoundException("can not found this account");
//		}
//			return new Account(account.getUsername(), account.getPassword(),
//					AuthorityUtils.createAuthorityList("USER"));
//		}
//	}
}

