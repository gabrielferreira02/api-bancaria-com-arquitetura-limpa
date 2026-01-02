package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.CreateAccountCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;

public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountGateway accountGateway;

    public CreateAccountUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public Account execute(CreateAccountCommand command) {
        Account account = new Account(
                command.firstName(),
                command.lastName(),
                command.password(),
                command.cpf());
        return accountGateway.createAccount(account);
    }
}
