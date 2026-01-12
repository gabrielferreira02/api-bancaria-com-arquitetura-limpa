package com.gabrielferreira02.BancoAPI.core.commands;

import java.math.BigDecimal;
import java.util.UUID;

public record AddBalanceCommand(UUID id, BigDecimal amount) {
}
