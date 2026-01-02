package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;

import java.util.UUID;

public class UpdateAccountPasswordUseCaseImpl implements UpdateAccountPasswordUseCase {

    private final AccountGateway accountGateway;

    public UpdateAccountPasswordUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public Account execute(UUID id, String password) {
        if(id == null) throw new IllegalArgumentException("Id inválido");
        Account account = accountGateway.getAccountById(id);
        if(account == null) throw new IllegalArgumentException("Conta não encontrada");
        account.setPassword(password);
        return accountGateway.updateAccountPassword(account);
    }
}
