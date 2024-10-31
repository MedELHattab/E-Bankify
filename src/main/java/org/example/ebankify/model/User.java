package org.example.ebankify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private int age;

    @Column(name = "monthly_income", nullable = false)
    private Double monthlyIncome;

    @Column(name = "credit_score", nullable = false)
    private int creditScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;




}
