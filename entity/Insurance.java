package com.example.demo.entity;
 
import java.time.LocalDate;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    private String insuranceId; // Primary key as String
 
    private String insuranceType;
    private double insuredAmount;
    private double claimableAmount;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private String createdBy;
    private LocalDate createdDate;
    private String lastUpdatedBy;
    private LocalDate lastUpdatedDate;
 
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
 
    @OneToMany(mappedBy = "insurance")
    @JsonIgnore
    private List<ClaimRequest> claimRequests;
 
	public String getInsuranceId() {
		return insuranceId;
	}
 
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
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
 
	public LocalDate getPolicyStartDate() {
		return policyStartDate;
	}
 
	public void setPolicyStartDate(LocalDate policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
 
	public LocalDate getPolicyEndDate() {
		return policyEndDate;
	}
 
	public void setPolicyEndDate(LocalDate policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
 
	public String getCreatedBy() {
		return createdBy;
	}
 
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
 
	public Member getMember() {
		return member;
	}
 
	public void setMember(Member member) {
		this.member = member;
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
}