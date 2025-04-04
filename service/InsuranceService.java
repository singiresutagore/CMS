package com.example.demo.service;

import com.example.demo.entity.Insurance;
import com.example.demo.entity.InsuranceRates;
import com.example.demo.entity.Member;
import com.example.demo.repository.InsuranceRatesRepository;
import com.example.demo.repository.InsuranceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private InsuranceRatesRepository insuranceRatesRepository;

    @Transactional
    public Insurance saveInsuranceDetails(String insuranceType, Member member) {
        InsuranceRates rates = insuranceRatesRepository.findById(insuranceType)
                .orElseThrow(() -> new EntityNotFoundException("Insurance type not found: " + insuranceType));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memId = authentication.getName();
        Insurance insurance = new Insurance();
        insurance.setInsuranceId(generateInsuranceId());
        insurance.setInsuranceType(insuranceType);
        insurance.setCreatedBy(memId);
        insurance.setLastUpdatedBy(memId);
        insurance.setInsuredAmount(rates.getInsuredAmount());
        insurance.setClaimableAmount(rates.getClaimableAmount());
        insurance.setPolicyStartDate(LocalDate.now());
        insurance.setPolicyEndDate(LocalDate.now().plusYears(1));
        insurance.setCreatedDate(LocalDate.now());
        insurance.setLastUpdatedDate(LocalDate.now());
        insurance.setMember(member);
        member.getInsurances().add(insurance);
        return insuranceRepository.save(insurance);
    }

    public InsuranceDetails getInsuranceDetails(String insuranceType) {
        InsuranceRates rates = insuranceRatesRepository.findById(insuranceType)
                .orElseThrow(() -> new EntityNotFoundException("Insurance type not found: " + insuranceType));
        return new InsuranceDetails(rates.getInsuredAmount(), rates.getClaimableAmount());
    }

    private static final String INSURANCE_ID_PREFIX = "POL-";

    private String generateInsuranceId() {
        long count = insuranceRepository.count();
        return INSURANCE_ID_PREFIX + String.format("%05d", count + 1);
    }

    public static class InsuranceDetails {
        private double insuredAmount;
        private double claimAmount;

        public InsuranceDetails(double insuredAmount, double claimAmount) {
            this.insuredAmount = insuredAmount;
            this.claimAmount = claimAmount;
        }

        public double getInsuredAmount() {
            return insuredAmount;
        }

        public double getClaimAmount() {
            return claimAmount;
        }
    }
}