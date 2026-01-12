package com.gabrielferreira02.BancoAPI.infrastructure.beans;

import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;
import com.gabrielferreira02.BancoAPI.core.gateway.TransactionGateway;
import com.gabrielferreira02.BancoAPI.core.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountGateway accountGateway) {
        return new CreateAccountUseCaseImpl(accountGateway);
    }

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(TransactionGateway transactionGateway,
                                                             AccountGateway accountGateway) {
        return new CreateTransactionUseCaseImpl(transactionGateway, accountGateway);
    }

    @Bean
    public DeleteAccountUseCase deleteAccountUseCase(AccountGateway accountGateway) {
        return new DeleteAccountUseCaseImpl(accountGateway);
    }

    @Bean
    public GetAccountByIdUseCase getAccountByIdUseCase(AccountGateway accountGateway) {
        return new GetAccountByIdUseCaseImpl(accountGateway);
    }

    @Bean
    public GetTransactionsByAccountUseCase getTransactionsByAccountUseCase(TransactionGateway transactionGateway) {
        return new GetTransactionsByAccountUseCaseImpl(transactionGateway);
    }

    @Bean
    public UpdateAccountUseCase updateAccountUseCase(AccountGateway accountGateway) {
        return new UpdateAccountUseCaseImpl(accountGateway);
    }

    @Bean
    public UpdateAccountPasswordUseCase updateAccountPasswordUseCase(AccountGateway accountGateway) {
        return new UpdateAccountPasswordUseCaseImpl(accountGateway);
    }

    @Bean
    public AddBalanceUseCase addBalanceUseCase(AccountGateway accountGateway) {
        return new AddBalanceUseCaseImpl(accountGateway);
    }
}
