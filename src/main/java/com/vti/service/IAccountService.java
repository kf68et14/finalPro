package com.vti.service;

import com.vti.form.AccountRequestFormForCreate;
import com.vti.form.AccountRequestFormForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;

import java.util.List;

public interface IAccountService {
    Page<Account> getAllAccounts(String search, Pageable page, AccountFilterForm filterForm);

    void createGroup(AccountRequestFormForCreate form);

    Account getAccountByID(int id);

    void updateAccount(int id, AccountRequestFormForUpdate form);

    void deleteAccounts(List<Integer> ids);
}
