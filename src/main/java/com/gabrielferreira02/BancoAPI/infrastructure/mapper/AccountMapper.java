package com.gabrielferreira02.BancoAPI.infrastructure.mapper;

import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.infrastructure.persistence.AccountEntity;
import org.springframework.stereotype.Component;

public class AccountMapper {

    public static Account entityToDomain(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getCpf(),
                entity.getPassword(),
                entity.getBalance(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static AccountEntity domainToEntity(Account domain) {
        return AccountEntity.builder()
                .id(domain.getId())
                .cpf(domain.getCpf())
                .balance(domain.getBalance())
                .createdAt(domain.getCreatedAt())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .password(domain.getPassword())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
}
