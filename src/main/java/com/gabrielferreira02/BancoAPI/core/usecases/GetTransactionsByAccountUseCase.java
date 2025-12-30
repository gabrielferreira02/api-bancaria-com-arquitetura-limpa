package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.entities.Transaction;

import java.util.List;
import java.util.UUID;

public interface GetTransactionsByAccountUseCase {
    List<Transaction> execute(UUID id);
}
