package com.gabrielferreira02.BancoAPI.core.usecases;

import java.util.UUID;

public interface DeleteAccountUseCase {
    void execute(UUID id);
}
