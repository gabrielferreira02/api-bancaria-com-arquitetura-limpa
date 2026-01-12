package com.gabrielferreira02.BancoAPI.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    private UUID id;
    @Min(value = 0, message = "Valor precisa ser maior que 0")
    private BigDecimal amount;
    @Column(nullable = false)
    private UUID receiver;
    @Column(nullable = false)
    private UUID sender;
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
