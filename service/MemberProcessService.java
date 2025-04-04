package com.example.demo.service;

import com.example.demo.entity.ClaimRequest;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberProcessService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ClaimProcessService claimRequestService;

	
	 public List<ClaimRequest> getClaimRequestsByMemberId(String memberId) {
	 return claimRequestService.getClaimRequestsByMemberId(memberId); }
	 

    public Member getMemberById(String memberId){
        Optional<Member> member = memberRepository.findById(memberId);
        return member.orElse(null);
    }
}