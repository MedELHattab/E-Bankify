package org.example.ebankify.service;

import org.example.ebankify.model.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account);
    Optional<Account> getAccountById(Long id);
    List<Account> getAllAccounts();
    Account updateAccount(Long id, Account accountDetails);
    void deleteAccount(Long id);
}
