package com.vti.service;

import com.vti.dto.AccountResponseDTO;
import com.vti.entity.Account;
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
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private IAccountRepository repository;

	public Page<Account> getAllAccounts(String search, Pageable pageable, AccountFilterForm filterForm) {
		AccountSpecificationBuilder specification = new AccountSpecificationBuilder(filterForm, search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public AccountResponseDTO getAccountByID(int id) throws AccountNotFoundException {
		Optional<Account> account = repository.findById(id);
		if (!account.isPresent()){
			throw new AccountNotFoundException("account not exists");
		}
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		accountResponseDTO.setId(id);
		accountResponseDTO.setUsername(account.get().getUsername());
		accountResponseDTO.setDepartmentName(account.get().getDepartment().getName());

		return accountResponseDTO;

	}

	public void createAccount(AccountRequestFormForCreate form) {
		this.modelMapper.getConfiguration().setSkipNullEnabled(true);
		TypeMap<Account, AccountRequestFormForCreate> propertyMapper
				= this.modelMapper.createTypeMap(Account.class, AccountRequestFormForCreate.class);
		propertyMapper.addMappings(mapper ->
				mapper.map(account -> account.getDepartment().getId(),
							AccountRequestFormForCreate::setDepartmentId));
		Account account = this.modelMapper.map(form, Account.class);

		repository.save(account);
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


