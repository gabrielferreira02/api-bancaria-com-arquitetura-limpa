package com.gabrielferreira02.BancoAPI.core.commands;

public record CreateAccountCommand(String firstName,
                                   String lastName,
                                   String cpf,
                                   String password) {
}
