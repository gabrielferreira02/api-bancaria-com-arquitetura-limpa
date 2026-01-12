package com.gabrielferreira02.BancoAPI.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    @Query("""
            SELECT t
            FROM TransactionEntity t
            WHERE t.sender = :id OR t.receiver = :id
            ORDER BY t.createdAt DESC
            """)
    List<TransactionEntity> getTransactionsByAccountId(@Param("id") UUID id);
}
