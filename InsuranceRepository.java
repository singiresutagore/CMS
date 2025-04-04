package com.example.demo.repository;

import com.example.demo.entity.Member;
import com.example.demo.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, String> {
    Optional<Insurance> findByInsuranceType(String insuranceType);
    Optional<Insurance> findByMember(Member member);
}