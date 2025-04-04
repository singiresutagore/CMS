package com.example.demo.dto;

import java.time.LocalDate;

public class ClaimDetailsDTO {
    private String requestId;
    private String firstName;
    private String lastName;
    private String insuranceType;
    private double insuredAmount;
    private LocalDate requestDate;
    private String claimReason;
    private double finalClaimAmount;
    private LocalDate amountAvailableDate;

    // Constructors, getters, and setters
    public ClaimDetailsDTO(){}

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public double getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(double insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getClaimReason() {
        return claimReason;
    }

    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }

    public double getFinalClaimAmount() {
        return finalClaimAmount;
    }

    public void setFinalClaimAmount(double finalClaimAmount) {
        this.finalClaimAmount = finalClaimAmount;
    }

    public LocalDate getAmountAvailableDate() {
        return amountAvailableDate;
    }

    public void setAmountAvailableDate(LocalDate amountAvailableDate) {
        this.amountAvailableDate = amountAvailableDate;
    }
}