package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClaimRequest;
import com.example.demo.entity.Insurance;
import com.example.demo.entity.Member;
import com.example.demo.repository.ClaimRequestRepository;
import com.example.demo.repository.InsuranceRepository;
import com.example.demo.repository.MemberRepository;

@Service
public class ClaimRequestService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private ClaimRequestRepository claimrequestRepository;
    public Member getdata(String memberId) {
  	  return memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found "));
        }
    
    public void getdata1(String memberId) {
        Optional<ClaimRequest> claimRequestOptional = claimrequestRepository.findByMemberId(memberId.trim());        System.out.println("ClaimRequest fetched: " + claimRequestOptional);
        if (claimRequestOptional.isPresent()) {
        	System.out.println("Condition met: Exception will be thrown for memberId " + memberId);
        	throw new RuntimeException("Member with " + memberId + " already raised claimed request");
        }
        else 
        {
            System.out.println("Condition not met: No ClaimRequest exists for memberId " + memberId);
        }    
        }
    public Insurance getInsuranceByMemberId(String memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            return insuranceRepository.findByMember(member).orElse(null); // Use Optional to handle possible null
        }
        return null;
    }
    public void saveData(ClaimRequest claimRequest)
    {
        Member member = memberRepository.findById(claimRequest.getMember().getMemberId()).orElseThrow();
        Insurance insurance = insuranceRepository.findByMember(member).orElseThrow(); // Use findByMember method to get Insurance by member
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memId = authentication.getName(); // Assuming username is memberId
        

        // Set the managed entities back to the ClaimRequest object
        claimRequest.setMember(member);
        claimRequest.setInsurance(insurance);
        claimRequest.setStatus("Requested");
        claimRequest.setRequestId(generateRequestId());
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = currentDateTime.toLocalDate();
        LocalDate futureDate = currentDate.plusDays(45);
        claimRequest.setCreatedDate(currentDate);
        // Save the claim request
        claimRequest.setAmountAvailableDate(currentDate);
        claimRequest.setCreatedBy(memId);
        claimRequest.setAmountAvailableDate(futureDate);
        claimRequest.setLastUpdatedDate(currentDate);
        claimRequest.setLastUpdatedBy(memId);
        claimrequestRepository.save(claimRequest);
        
    }
    private String generateRequestId() {
        long count = claimrequestRepository.count();
        return "CMS" + String.format("%05d", count + 1);
    }
}
