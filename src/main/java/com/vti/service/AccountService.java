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

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository repository;

	@Override
	public Page<Account> getAllAccounts(String search, Pageable pageable, AccountFilterForm filterForm) {
		AccountSpecificationBuilder specification = new AccountSpecificationBuilder(filterForm, search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public void createAccount(AccountRequestFormForCreate form) {
		repository.save(form.toEntity());
	}
	@Override
	public Account getAccountById(int id){
		return repository.findById(id).get();
	}

	public void updateAccount(int id, AccountRequestFormForUpdate form){
		Account account = repository.findById().get();

		account.setUsername(form.getUsername());
		account.setFirstName(form.getFirstName());
		account.setLastName(form.getLastName());
		account.setRole(form.getRole());
		account.setDepartment(form.getDepartment());

		repository.save(account);
	}

	@Transactional
	public void deleteAccounts(List<Integer> ids){
		repository.deleteByIdIn(ids);
	}



}


