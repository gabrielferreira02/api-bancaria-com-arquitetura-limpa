package com.gabrielferreira02.BancoAPI.infrastructure.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.Transaction;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "account")
public class AccountEntity {
    @Id
    private UUID id;
    @NotEmpty(message = "Nome inválido")
    private String firstName;
    @NotEmpty(message = "Sobrenome inválido")
    private String lastName;
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotEmpty
    private String password;
    @Min(value = 0, message = "Saldo não pode ser negativo")
    private BigDecimal balance;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
