package com.gabrielferreira02.BancoAPI.core.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private final BigDecimal amount;
    private final UUID receiver;
    private final UUID sender;
    private LocalDateTime createdAt;

    public Transaction(BigDecimal amount, UUID receiver, UUID sender) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor da transferência tem que ser maior que 0");
        if(receiver == null) throw new IllegalArgumentException("Destinatário inválido");
        if(sender == null) throw new IllegalArgumentException("Emissor inválido");

        this.amount = amount;
        this.receiver = receiver;
        this.sender = sender;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UUID getReceiver() {
        return receiver;
    }

    public UUID getSender() {
        return sender;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
