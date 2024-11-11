package org.example.ebankify.service;

import jakarta.validation.Valid;
import org.example.ebankify.dto.LoanDTO;
import org.example.ebankify.model.LoanStatusEnum;

public interface LoanService {
    LoanDTO applyForLoan(@Valid LoanDTO loanDTO);
    boolean checkEligibility(LoanDTO loanDTO);
    Double calculateEMI(Long loanId);
    void updateLoanStatus(Long loanId, LoanStatusEnum status);
}
