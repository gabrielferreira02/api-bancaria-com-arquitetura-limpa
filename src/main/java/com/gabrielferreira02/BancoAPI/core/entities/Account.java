package com.gabrielferreira02.BancoAPI.core.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private UUID id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String password;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Account(String firstName,
                   String lastName,
                   String password,
                   String cpf) {
        if(firstName == null || firstName.isEmpty()) throw new IllegalArgumentException("Primeiro nome não pode ser vazio");
        if(lastName == null || lastName.isEmpty()) throw new IllegalArgumentException("Primeiro nome não pode ser vazio");
        if(password.length() != 4) throw new IllegalArgumentException("Senha precisa ter 4 dígitos");
        if(!password.matches("[0-9]{4}")) throw new IllegalArgumentException("Senha pode conter apenas números");
        if(cpf.isEmpty()) throw new IllegalArgumentException("Cpf inválido");

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.balance = new BigDecimal(0);
        this.cpf = cpf;
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

//   constructor to mapping jpa entity
    public Account(UUID id,
                   String firstName,
                   String lastName,
                   String cpf,
                   String password,
                   BigDecimal balance,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.password = password;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void debit(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor da transferência precisa ser maior que 0");
        if(this.balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Valor da transferência maior que o saldo");
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void credit(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor da transferência precisa ser maior que 0");
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void update(String firstName,
                       String lastName) {
        if(firstName.isEmpty()) throw new IllegalArgumentException("Nome vazio");
        if(lastName.isEmpty()) throw new IllegalArgumentException("Sobrenome vazio");

        this.firstName = firstName;
        this.lastName = lastName;
        this.updatedAt = LocalDateTime.now();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(!password.matches("[0-9]{4}")) throw new IllegalArgumentException("Senha inválida");
        this.password = password;
        this.updatedAt = LocalDateTime.now();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
