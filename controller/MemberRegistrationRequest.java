package com.example.demo.controller;

import java.time.LocalDate;

public class MemberRegistrationRequest {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String address;
    private String contactNo;
    private String email;
    private int nomineeCount;
    private String gender;
    private String insuranceType;
    private double insuredAmount;
    private double claimableAmount;
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNomineeCount() {
		return nomineeCount;
	}
	public void setNomineeCount(int nomineeCount) {
		this.nomineeCount = nomineeCount;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public double getClaimableAmount() {
		return claimableAmount;
	}
	public void setClaimableAmount(double claimableAmount) {
		this.claimableAmount = claimableAmount;
	}

    
    // Getters and setters for all fields
}