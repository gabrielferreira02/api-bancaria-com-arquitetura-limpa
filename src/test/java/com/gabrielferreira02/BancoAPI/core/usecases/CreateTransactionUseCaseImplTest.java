package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.CreateTransactionCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.entities.Transaction;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;
import com.gabrielferreira02.BancoAPI.core.gateway.TransactionGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTransactionUseCaseImplTest {

    @Mock
    private TransactionGateway transactionGateway;
    @Mock
    private AccountGateway accountGateway;
    @InjectMocks
    private CreateTransactionUseCaseImpl createTransactionUseCase;

    @Test
    @DisplayName("Should create a transaction successfully")
    void shouldCreateTransactionSuccessfully() {
        CreateTransactionCommand command = new CreateTransactionCommand(UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(100));
        Account sender = new Account("sender", "last name", "1234", "12345678901");
        sender.credit(BigDecimal.valueOf(110));
        Account receiver = new Account("receiver", "last name", "1234", "12345678901");
        Transaction transaction = new Transaction(command.amount(), command.receiverId(), command.senderId());

        when(accountGateway.getAccountById(command.senderId())).thenReturn(sender);
        when(accountGateway.getAccountById(command.receiverId())).thenReturn(receiver);
        when(transactionGateway.createTransaction(any(Transaction.class))).thenReturn(transaction);

        Transaction response = createTransactionUseCase.execute(command);

        assertNotNull(response);
        assertEquals(command.amount(), response.getAmount());
    }

    @Test
    @DisplayName("Should fail creating transaction because amount is invalid")
    void shouldFailCreatingTransactionWithInvalidAmount() {
        CreateTransactionCommand command = new CreateTransactionCommand(UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(0));
        assertThrows(IllegalArgumentException.class, () -> {
           createTransactionUseCase.execute(command);
        });
    }

    @Test
    @DisplayName("Should fail creating transaction because amount is greater than sender balance")
    void shouldFailCreatingTransactionWithAmountGreaterThanSenderBalance() {
        CreateTransactionCommand command = new CreateTransactionCommand(UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(120));
        Account sender = new Account("sender", "last name", "1234", "12345678901");
        sender.credit(BigDecimal.valueOf(110));
        Account receiver = new Account("receiver", "last name", "1234", "12345678901");
        Transaction transaction = new Transaction(command.amount(), command.receiverId(), command.senderId());

        when(accountGateway.getAccountById(command.senderId())).thenReturn(sender);
        when(accountGateway.getAccountById(command.receiverId())).thenReturn(receiver);

        assertThrows(IllegalArgumentException.class, () -> {
            createTransactionUseCase.execute(command);
        });
    }

    @Test
    @DisplayName("Should fail creating transaction with invalid receiver id")
    void shouldFailCreatingTransactionWithInvalidReceiverId() {
        CreateTransactionCommand command = new CreateTransactionCommand(UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(110));
        Account sender = new Account("sender", "last name", "1234", "12345678901");
        sender.credit(BigDecimal.valueOf(110));

        when(accountGateway.getAccountById(command.senderId())).thenReturn(sender);
        when(accountGateway.getAccountById(command.receiverId())).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> {
            createTransactionUseCase.execute(command);
        });
    }

    @Test
    @DisplayName("Should fail creating transaction with invalid sender id")
    void shouldFailCreatingTransactionWithInvalidSenderId() {
        CreateTransactionCommand command = new CreateTransactionCommand(UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(110));

        when(accountGateway.getAccountById(command.senderId())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            createTransactionUseCase.execute(command);
        });
    }
}