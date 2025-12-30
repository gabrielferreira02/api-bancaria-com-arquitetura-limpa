package com.gabrielferreira02.BancoAPI.core.commands;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateTransactionCommand(UUID senderId,
                                       UUID receiverId,
                                       BigDecimal amount) {
}
