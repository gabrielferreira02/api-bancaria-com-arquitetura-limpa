CREATE TABLE account (
    id UUID PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    password VARCHAR(4) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    balance DECIMAL(19,2) NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    sender UUID NOT NULL,
    receiver UUID NOT NULL,
    amount DECIMAL(19,2) NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_transaction_sender FOREIGN KEY (sender) REFERENCES account(id),
    CONSTRAINT fk_transaction_receiver FOREIGN KEY (receiver) REFERENCES account(id)
);
