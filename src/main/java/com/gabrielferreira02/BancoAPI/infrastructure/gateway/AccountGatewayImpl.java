package com.gabrielferreira02.BancoAPI.infrastructure.gateway;

import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;
import com.gabrielferreira02.BancoAPI.infrastructure.mapper.AccountMapper;
import com.gabrielferreira02.BancoAPI.infrastructure.persistence.AccountEntity;
import com.gabrielferreira02.BancoAPI.infrastructure.persistence.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class AccountGatewayImpl implements AccountGateway {

    private final AccountRepository accountRepository;

    @Override
    public void deleteAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public Account createAccount(Account account) {
        AccountEntity newAccount = accountRepository.save(AccountMapper.domainToEntity(account));
        return AccountMapper.entityToDomain(newAccount);
    }

    @Override
    public Account updateAccount(Account account) {
        AccountEntity updatedAccount = accountRepository.save(AccountMapper.domainToEntity(account));
        return AccountMapper.entityToDomain(updatedAccount);
    }

    @Override
    public Account getAccountById(UUID accountId) {
        Optional<AccountEntity> account = accountRepository.findById(accountId);

        return account.map(AccountMapper::entityToDomain).orElse(null);
    }

    @Override
    public Account findByIdForUpdate(UUID accountId) {
        Optional<AccountEntity> account = accountRepository.findByIdForUpdate(accountId);

        return account.map(AccountMapper::entityToDomain).orElse(null);
    }

    @Override
    public Account updateAccountPassword(Account account) {
        AccountEntity updatedAccount = accountRepository.save(AccountMapper.domainToEntity(account));
        return AccountMapper.entityToDomain(updatedAccount);
    }
}
