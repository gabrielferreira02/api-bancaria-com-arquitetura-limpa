package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.entities.Transaction;
import com.gabrielferreira02.BancoAPI.core.gateway.TransactionGateway;

import java.util.List;
import java.util.UUID;

public class GetTransactionsByAccountUseCaseImpl implements GetTransactionsByAccountUseCase {

    private final TransactionGateway transactionGateway;

    public GetTransactionsByAccountUseCaseImpl(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public List<Transaction> execute(UUID id) {
        return transactionGateway.getTransactionsByAccount(id);
    }
}
