package org.example.ebankify.controller;

import jakarta.servlet.http.HttpSession;
import org.example.ebankify.dto.TransactionDTO;
import org.example.ebankify.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint to initiate a transaction
    @PostMapping("/initiate")
    public ResponseEntity<String> initiateTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO initiatedTransaction = transactionService.initiateTransaction(transactionDTO);
        return ResponseEntity.ok("Transaction initiated with ID: " + initiatedTransaction.getId());
    }

    // Endpoint to approve a transaction (only for employees)
    @PutMapping("/{transactionId}/approve")
    public ResponseEntity<String> approveTransaction(@PathVariable Long transactionId, HttpSession session) {
        return transactionService.approveTransaction(transactionId, session);
    }


    // Endpoint to get transaction history for a user
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(@PathVariable Long userId) {
        List<TransactionDTO> transactionHistory = transactionService.getTransactionHistory(userId);
        return ResponseEntity.ok(transactionHistory);
    }

    // Endpoint to get details of a specific transaction
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long transactionId) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok(transactionDTO);
    }
}
