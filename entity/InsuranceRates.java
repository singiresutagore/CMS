package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurancerates")
public class InsuranceRates {

    @Id
    private String insuranceType;
    private double insuredAmount;
    private double claimableAmount;
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
	public double getClaimableAmount() {
		return claimableAmount;
	}
	public void setClaimableAmount(double claimableAmount) {
		this.claimableAmount = claimableAmount;
	}
    
    
    
}