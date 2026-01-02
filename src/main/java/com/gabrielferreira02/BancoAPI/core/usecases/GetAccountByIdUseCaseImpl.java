package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;

import java.util.UUID;

public class GetAccountByIdUseCaseImpl implements GetAccountByIdUseCase {

    private final AccountGateway accountGateway;

    public GetAccountByIdUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public Account execute(UUID id) {
        if(id == null) throw new IllegalArgumentException("Id inválido");
        return accountGateway.getAccountById(id);
    }
}
