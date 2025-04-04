package com.example.demo.service;

import com.example.demo.entity.ClaimRequest;
import com.example.demo.repository.ClaimRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimProcessService {
    @Autowired
    private ClaimRequestRepository claimRequestRepository;

    public ClaimRequest getClaimRequestById(String memberId) {
        Optional<ClaimRequest> optionalClaimRequest = claimRequestRepository.findById(memberId);
        if (optionalClaimRequest.isPresent()) {
            System.err.println("ClaimRequest found for memberId: " + memberId);
            return optionalClaimRequest.get();
        } else {
            System.err.println("ClaimRequest not found for memberId: " + memberId);
            return null;
        }
    }

    public void approveClaim(String requestId, String document1, String document2, String document3) {
        ClaimRequest claimRequest = getClaimRequestById(requestId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memId = authentication.getName(); 
        if (claimRequest != null) {
            claimRequest.setStatus("Approved");
            claimRequest.setAmountAvailableDate(LocalDate.now().plusDays(10));
            claimRequest.setDocument1(document1);
            claimRequest.setDocument2(document2);
            claimRequest.setDocument3(document3);
            claimRequest.setComments(null);
            claimRequest.setLastUpdatedBy(memId);
            claimRequest.setLastUpdatedDate(LocalDate.now());
            claimRequestRepository.save(claimRequest);
        } else {
            System.err.println("ClaimRequest not found for approval, requestId: " + requestId);
        }
    }

    public void rejectClaim(String requestId, String rejectionReason) {
    	ClaimRequest claimRequest = getClaimRequestById(requestId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memId = authentication.getName();
        if (claimRequest != null) {
            claimRequest.setStatus("Rejected");
            claimRequest.setComments(rejectionReason);
            claimRequest.setDocument1(null);
            claimRequest.setDocument2(null);
            claimRequest.setDocument3(null);
            claimRequest.setAmountAvailableDate(null);
            claimRequest.setLastUpdatedBy(memId);
            claimRequest.setLastUpdatedDate(LocalDate.now());
            claimRequestRepository.save(claimRequest);
        } else {
            System.err.println("ClaimRequest not found for rejection, requestId: " + requestId);
        }
    }

	 public List<ClaimRequest> getClaimRequestsByMemberId(String memberId) {
	 List<ClaimRequest> claimRequests =
	 claimRequestRepository.findByMember_MemberId(memberId); if (claimRequests == null || claimRequests.isEmpty()) {
	 System.err.println("No ClaimRequests found for memberId: " + memberId); }
	  return claimRequests; }
	 
}