package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteAccountUseCaseImplTest {

    @Mock
    private AccountGateway accountGateway;
    @InjectMocks
    private DeleteAccountUseCaseImpl deleteAccountUseCase;

    @Test
    @DisplayName("Should delete account successfully")
    void shouldDeleteAccountSuccessfully() {
        UUID id = UUID.randomUUID();
        deleteAccountUseCase.execute(id);
        verify(accountGateway, times(1)).deleteAccount(id);
    }

    @Test
    @DisplayName("Should fail deleting account with invalid id")
    void shouldFailDeletingAccountWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
           deleteAccountUseCase.execute(null);
        });
    }
}