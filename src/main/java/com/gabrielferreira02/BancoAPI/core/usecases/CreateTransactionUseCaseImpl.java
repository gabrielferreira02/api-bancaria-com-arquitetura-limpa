package com.gabrielferreira02.BancoAPI.core.usecases;

import com.gabrielferreira02.BancoAPI.core.commands.CreateTransactionCommand;
import com.gabrielferreira02.BancoAPI.core.entities.Account;
import com.gabrielferreira02.BancoAPI.core.entities.Transaction;
import com.gabrielferreira02.BancoAPI.core.gateway.AccountGateway;
import com.gabrielferreira02.BancoAPI.core.gateway.TransactionGateway;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionGateway transactionGateway;
    private final AccountGateway accountGateway;

    public CreateTransactionUseCaseImpl(TransactionGateway transactionGateway, AccountGateway accountGateway) {
        this.transactionGateway = transactionGateway;
        this.accountGateway = accountGateway;
    }

    @Override
    public Transaction execute(CreateTransactionCommand command) {
        if(command == null) throw new IllegalArgumentException("Transação inexistente");

        Account sender = accountGateway.getAccountById(command.senderId());
        if(sender == null) throw new IllegalArgumentException("Conta do emissor não encontrada");
        Account receiver = accountGateway.getAccountById(command.receiverId());
        if(receiver == null) throw new IllegalArgumentException("Conta do destinatário não encontrada");

        sender.debit(command.amount());
        receiver.credit(command.amount());

        accountGateway.updateAccount(sender);
        accountGateway.updateAccount(receiver);

        Transaction newTransaction = new Transaction(
                command.amount(),
                receiver.getId(),
                sender.getId()
        );

        return transactionGateway.createTransaction(newTransaction);
    }
}
