package org.example.ebankify.repository;

import org.example.ebankify.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Additional custom queries can be added here if needed
}
