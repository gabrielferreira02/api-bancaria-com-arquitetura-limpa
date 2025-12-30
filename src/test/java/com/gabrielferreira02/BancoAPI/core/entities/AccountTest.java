package com.gabrielferreira02.BancoAPI.core.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Nested
    class CreateAccount {
        @Test
        @DisplayName("Should create new account with success")
        void shouldCreateNewAccountWithSuccess() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            assertEquals("first name", account.getFirstName());
            assertEquals("last name", account.getLastName());
            assertEquals("1234", account.getPassword());
            assertEquals("12345678901", account.getCpf());
            assertEquals(new BigDecimal(0), account.getBalance());
        }

        @Test
        @DisplayName("Should fail on create new account because first name is invalid")
        void shouldFailOnCreateNewAccountBecauseFirstNameIsInvalid() {
            assertThrows(IllegalArgumentException.class, () -> {
                Account account = new Account("", "last name", "1234", "12345678901");
            });
        }

        @Test
        @DisplayName("Should fail on create new account because last name is invalid")
        void shouldFailOnCreateNewAccountBecauseLastNameIsInvalid() {
            assertThrows(IllegalArgumentException.class, () -> {
                Account account = new Account("first name", "", "1234", "12345678901");
            });
        }

        @Test
        @DisplayName("Should fail on create new account because password is invalid")
        void shouldFailOnCreateNewAccountBecauseFirstPasswordInvalid() {
            assertThrows(IllegalArgumentException.class, () -> {
                Account account = new Account("first name", "last name", "12345", "12345678901");
            });
        }

        @Test
        @DisplayName("Should fail on create new account because password dont contain only numbers")
        void shouldFailOnCreateNewAccountBecausePasswordDontContainOnlyNumber() {
            assertThrows(IllegalArgumentException.class, () -> {
                Account account = new Account("", "last name", "123a", "12345678901");
            });
        }

        @Test
        @DisplayName("Should fail on create new account because CPF is invalid")
        void shouldFailOnCreateNewAccountBecauseCPFIsInvalid() {
            assertThrows(IllegalArgumentException.class, () -> {
                Account account = new Account("", "last name", "1234", "");
            });
        }
    }

    @Nested
    class Debit {
        @Test
        @DisplayName("Should debit with success")
        void shouldDebitWithSuccess() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            account.credit(new BigDecimal(100));
            account.debit(new BigDecimal(100));
            assertEquals(new BigDecimal(0), account.getBalance());
        }

        @Test
        @DisplayName("Should fail on debit because amount is greater than balance")
        void shouldFailOnDebitBecauseAmountIsGreaterThanBalance() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            account.credit(new BigDecimal(100));
            assertThrows(IllegalArgumentException.class, () -> {
                account.debit(new BigDecimal(101));
            });
        }

        @Test
        @DisplayName("Should fail on debit because amount is greater than balance")
        void shouldFailOnDebitBecauseAmountIsLessThanZero() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            account.credit(new BigDecimal(100));
            assertThrows(IllegalArgumentException.class, () -> {
                account.debit(new BigDecimal(-1));
            });
        }
    }

    @Nested
    class Credit {
        @Test
        @DisplayName("Should credit with success")
        void shouldCreditWithSuccess() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            account.credit(new BigDecimal(100));
            assertEquals(new BigDecimal(100), account.getBalance());
        }

        @Test
        @DisplayName("Should fail on credit because amount is negative")
        void shouldFailOnCreditBecauseAmountIsNegative() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            assertThrows(IllegalArgumentException.class, () -> {
                account.credit(new BigDecimal(-1));
            });
        }
    }

    @Nested
    class Update {
        @Test
        @DisplayName("Should update account data with success")
        void shouldUpdateAccountDataWithSuccess() {
            Account account = new Account("fisrt name", "last name", "1234", "12345678901");
            account.update("new first name", "last name");
            assertEquals("new first name", account.getFirstName());
            assertEquals("last name", account.getLastName());
        }

        @Test
        @DisplayName("Should fail on update account data because first name is invalid")
        void shouldFailOnUpdateAccountDataBecauseFirstNameIsInvalid() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            assertThrows(IllegalArgumentException.class, () -> {
                account.update("", "last name");
            });
        }

        @Test
        @DisplayName("Should fail on update account data because last name is invalid")
        void shouldFailOnUpdateAccountDataBecauseLastNameIsInvalid() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            assertThrows(IllegalArgumentException.class, () -> {
                account.update("new first name", "");
            });
        }
    }

    @Nested
    class UpdatePassword {
        @Test
        @DisplayName("Should update password with success")
        void shouldUpdatePasswordWithSuccess() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            account.setPassword("4321");
            assertEquals("4321", account.getPassword());
        }

        @Test
        @DisplayName("Should fail on update password because dont contain only numbers")
        void shouldFailOnUpdatePasswordBecauseDontContainOnlyNumbers() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            assertThrows(IllegalArgumentException.class, () -> {
                account.setPassword("123a");
            });
        }

        @Test
        @DisplayName("Should fail on update password because size is not equal to 4")
        void shouldFailOnUpdatePasswordBecauseSizeIsInvalid() {
            Account account = new Account("first name", "last name", "1234", "12345678901");
            assertThrows(IllegalArgumentException.class, () -> {
                account.setPassword("12345");
            });
        }
    }
}