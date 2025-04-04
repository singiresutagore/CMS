package com.example.demo.entity;
 
import jakarta.persistence.*;
import java.time.LocalDate;

import org.springframework.data.annotation.Version;
 
@Entity
@Table(name = "claim")
public class ClaimRequest {
    @Id
    private String requestId; // Primary key as String
 
    private LocalDate requestDate;
    private String claimReason;
    private String status;
    private double finalClaimAmount;
    private LocalDate amountAvailableDate; // Changed to LocalDate
    @Column(updatable = false)
    private String createdBy;
    @Column(updatable = false)
    private LocalDate createdDate;
    private String lastUpdatedBy;
    private LocalDate lastUpdatedDate; // Changed to LocalDate
    private String comments;
    private String document1;
    private String document2;
    private String document3;
 
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false) // Foreign key referring to Member
    private Member member;
 
    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false) // Foreign key referring to Insurance
    private Insurance insurance;
    
    
    @Version
    private int version;
	public String getRequestId() {
		return requestId;
	}
 
	public void setRequestId(String requestId) {
		this.requestId = requestId;
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
 
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
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
 
	public String getComments() {
		return comments;
	}
 
	public void setComments(String comments) {
		this.comments = comments;
	}
 
	public String getDocument1() {
		return document1;
	}
 
	public void setDocument1(String documents) {
		this.document1 = documents;
	}

 
	public String getDocument2() {
		return document2;
	}
 
	public void setDocument2(String document2) {
		this.document2 = document2;
	}
 
	public String getDocument3() {
		return document3;
	}
 
	public void setDocument3(String document3) {
		this.document3 = document3;
	}
 
	public Member getMember() {
		return member;
	}
 
	public void setMember(Member member) {
		this.member = member;
	}
 
	public Insurance getInsurance() {
		return insurance;
	}
 
	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
 
	public LocalDate getLastUpdatedDate() {
		return lastUpdatedDate;
	}
 
	public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
 
    
}