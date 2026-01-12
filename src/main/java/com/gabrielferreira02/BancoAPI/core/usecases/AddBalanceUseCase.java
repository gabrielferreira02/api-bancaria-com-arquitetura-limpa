package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.AddBalanceCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Account;

import java.math.BigDecimal;
import java.util.UUID;

public interface AddBalanceUseCase {
    Account execute(AddBalanceCommand command);
}
