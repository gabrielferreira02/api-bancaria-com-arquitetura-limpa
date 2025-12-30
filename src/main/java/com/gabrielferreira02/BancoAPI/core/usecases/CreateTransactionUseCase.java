package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.CreateTransactionCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Transaction;

public interface CreateTransactionUseCase {
    Transaction execute(CreateTransactionCommand command);
}
