package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.UpdateAccountCommand;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateAccountUseCaseImplTest {

    @Mock
    private AccountGateway accountGateway;
    @InjectMocks
    private UpdateAccountUseCaseImpl updateAccountUseCase;

    @Test
    @DisplayName("Should update account data successfully")
    void shouldUpdateAccountDataSuccessfully() {
        UpdateAccountCommand command = new UpdateAccountCommand(
                UUID.randomUUID(),
                "first updated",
                "last name"
        );

        Account account = new Account(
                "first name",
                "last name",
                "1234",
                "12345678901"
        );

        when(accountGateway.getAccountById(any(UUID.class))).thenReturn(account);
        when(accountGateway.updateAccount(any(Account.class))).thenReturn(account);

        Account response = updateAccountUseCase.execute(command);

        assertNotNull(response);
        assertEquals(command.firstName(), response.getFirstName());
        assertEquals(command.lastName(), response.getLastName());
    }

    @Test
    @DisplayName("Should fail updating account data with non-existent id")
    void shouldFailUpdatingAccountDataWithNonExistentId() {
        UpdateAccountCommand command = new UpdateAccountCommand(
                UUID.randomUUID(),
                "first updated",
                "last name"
        );

        when(accountGateway.getAccountById(any(UUID.class))).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            Account response = updateAccountUseCase.execute(command);
        });
    }

    @Test
    @DisplayName("Should fail updating account data with invalid data")
    void shouldFailUpdatingAccountDataWithInvalidData() {
        UpdateAccountCommand command = new UpdateAccountCommand(
                UUID.randomUUID(),
                "first updated",
                ""
        );

        Account account = new Account(
                "first name",
                "last name",
                "1234",
                "12345678901"
        );

        when(accountGateway.getAccountById(any(UUID.class))).thenReturn(account);

        assertThrows(IllegalArgumentException.class, () -> {
            Account response = updateAccountUseCase.execute(command);
        });
    }
}