package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.entities.Account;

import java.util.UUID;

public interface GetAccountByIdUseCase {
    Account execute(UUID id);
}
