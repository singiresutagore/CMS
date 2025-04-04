package com.example.demo.repository;

import com.example.demo.entity.ClaimRequest;
import com.example.demo.entity.Insurance;
import com.example.demo.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaimRequestRepository extends JpaRepository<ClaimRequest, String> {
    Optional<ClaimRequest> findByMemberAndInsurance(Member member, Insurance insurance);
    List<ClaimRequest> findByMember(com.example.demo.entity.Member member);
	List<ClaimRequest> findByMember_MemberId(String memberId);

    // Custom query to find ClaimRequest by memberId (foreign key)
    @Query("SELECT c FROM ClaimRequest c WHERE c.member.memberId = :memberId")
    Optional<ClaimRequest> findByMemberId(@Param("memberId") String memberId);
}