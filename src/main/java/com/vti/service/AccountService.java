package com.vti.service;

import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountRequestFormForCreate;
import com.vti.form.AccountRequestFormForUpdate;
import com.vti.repository.IAccountRepository;
import com.vti.specification.AccountSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository repository;

	public Page<Account> getAllAccounts(String search, Pageable pageable, AccountFilterForm filterForm) {
		AccountSpecificationBuilder specification = new AccountSpecificationBuilder(filterForm, search);

		return repository.findAll(specification.build(), pageable);
	}

	public List<Account> getAllAccountsV2() {
		return repository.findAll();
	}

	@Override
	public Account getAccountByID(int id) {
		return repository.findById(id).get();
	}

	public void createAccount(AccountRequestFormForCreate form) {

	}

	public void updateAccountPartially(int id, Map<String, Object> fields) {
		Optional<Account> account = repository.findById(id);
		
		if(account.isPresent()){
			fields.forEach((key, value)-> {
				Field field = ReflectionUtils.findField(Account.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, account.get(), value);
			});
		}

		repository.save(account.get());
	}

	public void updateAccount(int id, AccountRequestFormForUpdate form){
		Optional<Account> account = repository.findById(id);

		if(account.isPresent()){
			account.get().setUsername(form.getUsername());
			account.get().setFirstName(form.getFirstName());
			account.get().setLastName(form.getLastName());
			account.get().setRole(form.getRole());
//			account.get().setDepartment(form.getDepartment());
		}
		repository.save(account.get());
	}

	@Transactional
	public void deleteAccounts(List<Integer> ids){
		repository.deleteByIdIn(ids);
	}

}


