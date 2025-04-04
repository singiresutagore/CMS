package com.example.demo.dto;

public class ClaimRequestDTO {
    private String requestId;
    private String insuranceType;
    private String status;

    // Constructors, getters, and setters
    public ClaimRequestDTO() {}

    public ClaimRequestDTO(String requestId, String insuranceType, String status) {
        this.requestId = requestId;
        this.insuranceType = insuranceType;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}