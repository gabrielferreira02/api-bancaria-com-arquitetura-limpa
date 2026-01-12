package com.gabrielferreira02.BancoAPI.infrastructure.gateway;

import com.gabrielferreira02.BancoAPI.core.entities.Transaction;
import com.gabrielferreira02.BancoAPI.core.gateway.TransactionGateway;
import com.gabrielferreira02.BancoAPI.infrastructure.mapper.TransactionMapper;
import com.gabrielferreira02.BancoAPI.infrastructure.persistence.TransactionEntity;
import com.gabrielferreira02.BancoAPI.infrastructure.persistence.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class TransactionGatewayImpl implements TransactionGateway {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        TransactionEntity newTransaction = transactionRepository.save(TransactionMapper.toEntity(transaction));
        return TransactionMapper.toDomain(newTransaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(UUID accountId) {
        return transactionRepository.getTransactionsByAccountId(accountId)
                .stream()
                .map(TransactionMapper::toDomain)
                .toList();
    }
}
