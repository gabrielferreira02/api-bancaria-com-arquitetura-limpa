package com.gabrielferreira02.BancoAPI.core.gateway;

import com.gabrielferreira02.BancoAPI.core.entities.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionGateway {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getTransactionsByAccount(UUID accountId);
}
