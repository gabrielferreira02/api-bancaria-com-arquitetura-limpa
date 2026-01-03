package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.entities.Transaction;
import com.gabrielferreira02.BancoAPI.core.gateway.TransactionGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTransactionsByAccountUseCaseImplTest {

    @Mock
    private TransactionGateway transactionGateway;
    @InjectMocks
    private GetTransactionsByAccountUseCaseImpl getTransactionsByAccountUseCase;

    @Test
    @DisplayName("Should return account transactions successfuully")
    void shouldReturnAccountTransactionsSuccessfully() {
        UUID id = UUID.randomUUID();
        List<Transaction> transactions = List.of(
                new Transaction(BigDecimal.valueOf(100), id, UUID.randomUUID()),
                new Transaction(BigDecimal.valueOf(200), UUID.randomUUID(), id),
                new Transaction(BigDecimal.valueOf(10), id, UUID.randomUUID())
        );

        when(transactionGateway.getTransactionsByAccount(any(UUID.class))).thenReturn(transactions);

        List<Transaction> response = getTransactionsByAccountUseCase.execute(id);

        assertNotNull(response);
        assertEquals(transactions.size(), response.size());
    }
}