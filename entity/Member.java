package com.example.demo.entity;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "member")
public class Member {
    @Id
    private String memberId; // Primary key as String
 
    private String firstName;
    private String lastName;
    private String contactNo;
    private LocalDate dob; // Changed to LocalDate
    private String email;
    private String gender;
    private String address;
    private String createdBy;
    private LocalDate createdDate;
    private String lastUpdatedBy;
    private LocalDate lastUpdatedDate; // Changed to LocalDate
    private int nomineeCount;
    private String role;
    private String citizenType;
    private String password;
    @Version
    private int version;
 
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Insurance> insurances;
 
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<ClaimRequest> claimRequests;
 
	public String getMemberId() {
		return memberId;
	}
 
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
 
	public String getContactNo() {
		return contactNo;
	}
 
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
 
	public String getCreatedBy() {
		return createdBy;
	}
 
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
 
	public LocalDate getDob() {
		return dob;
	}
 
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String getGender() {
		return gender;
	}
 
	public void setGender(String gender) {
		this.gender = gender;
	}
 
	public String getAddress() {
		return address;
	}
 
	public void setAddress(String address) {
		this.address = address;
	}
 
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
 
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
 
	public LocalDate getLastUpdatedDate() {
		return lastUpdatedDate;
	}
 
	public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
 
	public int getNomineeCount() {
		return nomineeCount;
	}
 
	public void setNomineeCount(int nomineeCount) {
		this.nomineeCount = nomineeCount;
	}
 
	public String getRole() {
		return role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}
 
	public List<Insurance> getInsurances() {
		return insurances;
	}
 
	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}
 
	public List<ClaimRequest> getClaimRequests() {
		return claimRequests;
	}
 
	public void setClaimRequests(List<ClaimRequest> claimRequests) {
		this.claimRequests = claimRequests;
	}
 
	public LocalDate getCreatedDate() {
		return createdDate;
	}
 
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getCitizenType() {
		return citizenType;
	}

	public void setCitizenType(String citizenType) {
		this.citizenType = citizenType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	  public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Member() {
	        this.insurances = new ArrayList<>(); // Initialize the list
	    }
}
