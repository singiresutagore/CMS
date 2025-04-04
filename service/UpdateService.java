package com.example.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;



@Service
public class UpdateService {
	
	

    @Autowired
    private MemberRepository memberRepository;

    // Retrieve member by ID or throw an exception if not found
    public Member getMemberById(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member with ID " + memberId + " not found"));
    }

    // Save or update member
    public void saveMember(Member member) {
        memberRepository.save(member);
    }
}
