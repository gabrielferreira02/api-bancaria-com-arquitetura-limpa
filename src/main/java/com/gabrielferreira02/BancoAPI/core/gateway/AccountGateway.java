package com.gabrielferreira02.BancoAPI.core.gateway;

import com.gabrielferreira02.BancoAPI.core.entities.Account;

import java.util.UUID;

public interface AccountGateway {
    void deleteAccount(UUID accountId);
    Account createAccount(Account account);
    Account updateAccount(Account account);
    Account getAccountById(UUID accountId);
    Account updateAccountPassword(Account account);
}
