package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.InsuranceRates;

@Repository
public interface InsuranceRatesRepository extends JpaRepository<InsuranceRates, String> {
    // You can add custom queries here if needed
}