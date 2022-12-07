package com.vti.service;

import java.util.List;

import com.vti.specification.AccountSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;
import com.vti.repository.IAccountRepository;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository repository;

	@Override
	public Page<Account> getAllAccounts(String search, Pageable pageable, AccountFilterForm filterForm) {
		AccountSpecificationBuilder specification = new AccountSpecificationBuilder(filterForm, search);

		return repository.findAll(specification.build(), pageable);
	}
}


