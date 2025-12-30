package com.gabrielferreira02.BancoAPI.core.commands;

import java.util.UUID;

public record UpdateAccountCommand(
        UUID accountId,
        String firstName,
        String lastName
) {
}
