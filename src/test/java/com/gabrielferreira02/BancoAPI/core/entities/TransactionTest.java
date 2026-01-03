package com.gabrielferreira02.BancoAPI.core.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    @Nested
    class CreateTransaction {
        @Test
        @DisplayName("Should create transaction with success")
        void shouldCreateTransactionWithSuccess() {
            UUID sender = UUID.randomUUID();
            UUID receiver = UUID.randomUUID();
            Transaction transaction = new Transaction(
                    BigDecimal.valueOf(100),
                    receiver,
                    sender
            );

            assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
            assertEquals(sender, transaction.getSender());
            assertEquals(receiver, transaction.getReceiver());
        }

        @Test
        @DisplayName("Should fail creating transaction with same sender and receiver id")
        void shouldFailCreatingTransactionWithSameSenderAndReceiverId() {
            UUID id = UUID.randomUUID();
            assertThrows(IllegalArgumentException.class, () -> {
                Transaction transaction = new Transaction(
                        BigDecimal.valueOf(100),
                        id,
                        id
                );
            });
        }

        @Test
        @DisplayName("Should fail on create transaction because amount is less than or equal to 0")
        void shouldFailOnCreateTransactionBecauseAmountIsInvalid() {
            UUID sender = UUID.randomUUID();
            UUID receiver = UUID.randomUUID();
            assertThrows(IllegalArgumentException.class, () -> {
                Transaction transaction = new Transaction(
                        BigDecimal.valueOf(0),
                        receiver,
                        sender
                );
            });
        }

        @Test
        @DisplayName("Should fail on create transaction because sender is null")
        void shouldFailOnCreateTransactionBecauseSenderIsNull() {
            UUID receiver = UUID.randomUUID();
            assertThrows(IllegalArgumentException.class, () -> {
                Transaction transaction = new Transaction(
                        BigDecimal.valueOf(0),
                        receiver,
                        null
                );
            });
        }

        @Test
        @DisplayName("Should fail on create transaction because receiver is null")
        void shouldFailOnCreateTransactionBecauseReceiverIsNull() {
            UUID sender = UUID.randomUUID();
            assertThrows(IllegalArgumentException.class, () -> {
                Transaction transaction = new Transaction(
                        BigDecimal.valueOf(0),
                        null,
                        sender
                );
            });
        }
    }
}