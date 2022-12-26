package com.vti.service;

import com.vti.dto.AccountResponseDTO;
import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountRequestFormForCreate;
import com.vti.form.AccountRequestFormForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

public interface IAccountService {

//    Page<Account> getAllAccounts (String search, Pageable page, AccountFilterForm filterForm);
    Page<Account> getAllAccountsV2 (String search, Pageable page);


    AccountResponseDTO getAccountByID(int id);

    void updateAccount(int id, AccountRequestFormForUpdate form);
    void updateAccountPartially(int id, Map<String, Object> fields);

    void deleteAccounts(List<Integer> ids);

    void createAccount(AccountRequestFormForCreate form);
}
