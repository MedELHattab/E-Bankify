package org.example.ebankify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private Double principal;

    private Double interestRate;

    private int termMonths;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private boolean approved;

    public boolean applyForLoan() {
        // Loan application logic
        return true; // Placeholder
    }

    public Double calculateEMI() {
        // EMI calculation logic
        return principal * (interestRate / 100 / 12); // Simplified formula for demonstration
    }
}
