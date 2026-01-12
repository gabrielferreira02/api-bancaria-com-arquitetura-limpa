package com.gabrielferreira02.BancoAPI.infrastructure.mapper;

import com.gabrielferreira02.BancoAPI.core.entities.Transaction;
import com.gabrielferreira02.BancoAPI.infrastructure.persistence.TransactionEntity;

public class TransactionMapper {
    public static Transaction toDomain(TransactionEntity transaction) {
        return new Transaction(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getReceiver(),
                transaction.getSender(),
                transaction.getCreatedAt()
        );
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .receiver(transaction.getReceiver())
                .id(transaction.getId())
                .sender(transaction.getSender())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
