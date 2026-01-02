package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.CreateAccountCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseImplTest {

    @Mock
    private AccountGateway accountGateway;
    @InjectMocks
    private CreateAccountUseCaseImpl createAccountUseCase;

    @Test
    @DisplayName("Should create a new account with success")
    void shouldCreateWithSuccess() {
        CreateAccountCommand command = new CreateAccountCommand(
                "first name",
                "last name",
                "12345678901",
                "1234");

        Account account = new Account(
                command.firstName(),
                command.lastName(),
                command.password(),
                command.cpf()
        );

        when(accountGateway.createAccount(any(Account.class))).thenReturn(account);

        Account response = createAccountUseCase.execute(command);

        assertNotNull(response);
        assertEquals(command.firstName(), response.getFirstName());
        assertEquals(command.lastName(), response.getLastName());
        assertEquals(command.cpf(), response.getCpf());
    }

    @Test
    @DisplayName("Should fail creating new account because first name is invalid")
    void shouldFailCreatingAccountBecauseFirstNameIsInvalid() {
        CreateAccountCommand command = new CreateAccountCommand(
                "",
                "last name",
                "12345678901",
                "1234");

        assertThrows(IllegalArgumentException.class, () -> {
            Account account = new Account(
                    command.firstName(),
                    command.lastName(),
                    command.password(),
                    command.cpf()
            );
        });
    }

    @Test
    @DisplayName("Should fail creating new account because last name is invalid")
    void shouldFailCreatingAccountBecauseLastNameIsInvalid() {
        CreateAccountCommand command = new CreateAccountCommand(
                "first name",
                "",
                "12345678901",
                "1234");

        assertThrows(IllegalArgumentException.class, () -> {
            Account account = new Account(
                    command.firstName(),
                    command.lastName(),
                    command.password(),
                    command.cpf()
            );
        });
    }

    @Test
    @DisplayName("Should fail creating new account because cpf is invalid")
    void shouldFailCreatingAccountBecauseCPFIsInvalid() {
        CreateAccountCommand command = new CreateAccountCommand(
                "first name",
                "last name",
                "",
                "1234");

        assertThrows(IllegalArgumentException.class, () -> {
            Account account = new Account(
                    command.firstName(),
                    command.lastName(),
                    command.password(),
                    command.cpf()
            );
        });
    }

    @Test
    @DisplayName("Should fail creating new account because password is invalid")
    void shouldFailCreatingAccountBecausePasswordIsInvalid() {
        CreateAccountCommand command = new CreateAccountCommand(
                "first name",
                "last name",
                "12345678901",
                "12345");

        assertThrows(IllegalArgumentException.class, () -> {
            Account account = new Account(
                    command.firstName(),
                    command.lastName(),
                    command.password(),
                    command.cpf()
            );
        });
    }
}