package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.CreateAccountCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Account;

public interface CreateAccountUseCase {
    Account execute(CreateAccountCommand command);
}
