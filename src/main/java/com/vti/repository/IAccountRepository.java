package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Account;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    void deleteByIdIn(List<Integer> ids);

    Account findByUsername(String username);

    // Account findByUsername(String uss);
}
