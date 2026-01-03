package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAccountByIdUseCaseImplTest {

    @Mock
    private AccountGateway accountGateway;
    @InjectMocks
    private GetAccountByIdUseCaseImpl getAccountByIdUseCase;

    @Test
    @DisplayName("Should get account successfully")
    void shouldGetAccountSuccessfully() {
        UUID id = UUID.randomUUID();
        Account account = new Account("first name", "last name", "1234", "12345678901");

        when(accountGateway.getAccountById(any(UUID.class))).thenReturn(account);

        Account response = getAccountByIdUseCase.execute(id);

        assertNotNull(response);
        assertEquals(account.getFirstName(), response.getFirstName());
        assertEquals(account.getLastName(), response.getLastName());
        assertEquals(account.getCpf(), response.getCpf());
    }

    @Test
    @DisplayName("Should fail getting account with invalid id")
    void shouldFailDeletingAccountWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> {
            getAccountByIdUseCase.execute(null);
        });
    }

    @Test
    @DisplayName("Should fail getting account with non-existent id")
    void shouldFailGettingAccountWithNonExistentId() {
        when(accountGateway.getAccountById(any(UUID.class))).thenReturn(null);

        Account response = getAccountByIdUseCase.execute(UUID.randomUUID());

        assertNull(response);
    }
}