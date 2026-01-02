package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;

import java.util.UUID;

public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {

    private final AccountGateway accountGateway;

    public DeleteAccountUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public void execute(UUID id) {
        if(id == null) throw new IllegalArgumentException("Id inválido");
        accountGateway.deleteAccount(id);
    }
}
