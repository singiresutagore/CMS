package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClaimRequest;
import com.example.demo.entity.Insurance;
import com.example.demo.entity.Member;
import com.example.demo.repository.ClaimRequestRepository;
import com.example.demo.repository.InsuranceRepository;
import com.example.demo.repository.MemberRepository;

@Service
public class SearchService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    ClaimRequestRepository claimsRepository;
    
    public Member getMemberById(String memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public Optional<Insurance> getInsurancesByMember(Member member) {
        return insuranceRepository.findByMember(member);
    }

    public List<ClaimRequest> getClaimsByMember(Member member) {
        return claimsRepository.findByMember(member);
    }
}
