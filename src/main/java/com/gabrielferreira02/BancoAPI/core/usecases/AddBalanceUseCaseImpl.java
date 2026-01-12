package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.AddBalanceCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;

import java.math.BigDecimal;
import java.util.UUID;

public class AddBalanceUseCaseImpl implements AddBalanceUseCase{

    private final AccountGateway accountGateway;

    public AddBalanceUseCaseImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public Account execute(AddBalanceCommand command) {
        if(command.amount().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor precisa ser maior que 0");

        Account account = accountGateway.getAccountById(command.id());
        if(account == null) throw new IllegalArgumentException("Conta não encontrada");

        account.credit(command.amount());
        return accountGateway.updateAccount(account);
    }
}
