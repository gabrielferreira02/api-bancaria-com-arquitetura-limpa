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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateAccountPasswordUseCaseImplTest {

    @Mock
    private AccountGateway accountGateway;
    @InjectMocks
    private UpdateAccountPasswordUseCaseImpl updateAccountPasswordUseCase;

    @Test
    @DisplayName("Should update password successfully")
    void shouldUpdatePasswordSuccessfully() {
        String newPassword = "1357";
        UUID id = UUID.randomUUID();
        Account account =  new Account("first name", "last name", "1234", "12345678901");

        when(accountGateway.getAccountById(any(UUID.class))).thenReturn(account);
        when(accountGateway.updateAccountPassword(any(Account.class))).thenReturn(account);

        Account response = updateAccountPasswordUseCase.execute(id, newPassword);

        assertNotNull(response);
        assertEquals(newPassword, response.getPassword());
        verify(accountGateway, times(1)).updateAccountPassword(any(Account.class));
    }

    @Test
    @DisplayName("Should fail updating invalid password")
    void shouldFailUpdatingInvalidPassword() {
        String newPassword = "13579";
        UUID id = UUID.randomUUID();
        Account account =  new Account("first name", "last name", "1234", "12345678901");

        when(accountGateway.getAccountById(any(UUID.class))).thenReturn(account);

        assertThrows(IllegalArgumentException.class, () -> {
            updateAccountPasswordUseCase.execute(id, newPassword);
        });
    }

    @Test
    @DisplayName("Should fail updating password with non-existent id")
    void shouldFailUpdatingPasswordWithNonExistentId() {
        String newPassword = "1357";
        UUID id = UUID.randomUUID();

        when(accountGateway.getAccountById(any(UUID.class))).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            updateAccountPasswordUseCase.execute(id, newPassword);
        });
    }

    @Test
    @DisplayName("Should fail updating password with invalid id")
    void shouldFailUpdatingPasswordWithInvalidId() {
        String newPassword = "1357";

        assertThrows(IllegalArgumentException.class, () -> {
            updateAccountPasswordUseCase.execute(null, newPassword);
        });
    }
}