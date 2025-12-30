package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.entities.Account;

import java.util.UUID;

public interface UpdateAccountPasswordUseCase {
    Account execute(UUID id, String password);
}
