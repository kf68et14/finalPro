package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;

public interface IAccountService {
    Page<Account> getAllAccounts(String search, Pageable page, AccountFilterForm filterForm);
}
