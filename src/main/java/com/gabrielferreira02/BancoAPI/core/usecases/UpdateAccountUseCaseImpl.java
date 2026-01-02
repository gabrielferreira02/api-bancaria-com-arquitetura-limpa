package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.UpdateAccountCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;

public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountGateway accountGateway;

    public UpdateAccountUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public Account execute(UpdateAccountCommand command) {
        Account account = accountGateway.getAccountById(command.accountId());
        if(account == null) throw new IllegalArgumentException("Conta inexistente");
        account.update(command.firstName(), command.lastName());
        return accountGateway.updateAccount(account);
    }
}
