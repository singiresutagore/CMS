package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import com.example.demo.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private InsuranceService insuranceService;

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody MemberRegistrationRequest request) {
        try {
            Member savedMember = memberService.registerMember(request);
            insuranceService.saveInsuranceDetails(request.getInsuranceType(), savedMember);

            Map<String, String> response = new HashMap<>();
            response.put("message", String.format("Dear Admin, The member added successfully with member ID: %s", savedMember.getMemberId()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/insurance-details")
    public InsuranceService.InsuranceDetails getInsuranceDetails(@RequestParam String insuranceType) {
        return insuranceService.getInsuranceDetails(insuranceType);
    }
}