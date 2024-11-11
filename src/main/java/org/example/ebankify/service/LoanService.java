package org.example.ebankify.service;

import org.example.ebankify.dto.LoanDTO;

public interface LoanService {
    LoanDTO applyForLoan(LoanDTO loanDTO);
    boolean checkEligibility(LoanDTO loanDTO);
    Double calculateEMI(Long loanId);
}
