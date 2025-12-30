package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.UpdateAccountCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Account;

public interface UpdateAccountUseCase {
    Account execute(UpdateAccountCommand command);
}
