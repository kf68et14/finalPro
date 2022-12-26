package com.vti.service;

import com.vti.dto.AccountResponseDTO;
import com.vti.entity.Account;
import com.vti.entity.Role;
import com.vti.form.AccountRequestFormForCreate;
import com.vti.form.AccountRequestFormForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

public interface IAccountService {

<<<<<<< HEAD
    Page<Account> getAllAccounts (String search, Pageable page, Role role);
=======
//    Page<Account> getAllAccounts (String search, Pageable page, AccountFilterForm filterForm);
    Page<Account> getAllAccountsV2 (String search, Pageable page);
>>>>>>> 46c32aff8e1dec8f31553e12da8bf8eb31012b41


    AccountResponseDTO getAccountByID(int id) throws AccountNotFoundException;

    void updateAccount(int id, AccountRequestFormForUpdate form);
    void updateAccountPartially(int id, Map<String, Object> fields) throws AccountNotFoundException;

    void deleteAccounts(List<Integer> ids) throws AccountNotFoundException;

    void createAccount(AccountRequestFormForCreate form);

    void deleteById(int id) throws AccountNotFoundException;

    boolean isAccountExitById(int id);
}
