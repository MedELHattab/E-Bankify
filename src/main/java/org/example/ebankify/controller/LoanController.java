package org.example.ebankify.controller;

import jakarta.validation.Valid;
import org.example.ebankify.dto.LoanDTO;
import org.example.ebankify.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<String> applyForLoan(@Valid @RequestBody LoanDTO loanDTO) {
        LoanDTO appliedLoan = loanService.applyForLoan(loanDTO);
        return ResponseEntity.ok("Loan applied successfully with ID: " + appliedLoan.getId());
    }

    @GetMapping("/{loanId}/emi")
    public ResponseEntity<Double> calculateEMI(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.calculateEMI(loanId));
    }
}
