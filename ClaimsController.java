package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.Member;
import com.example.demo.entity.Insurance;
import com.example.demo.entity.ClaimRequest;
import com.example.demo.service.ClaimRequestService;
import com.example.demo.repository.ClaimRequestRepository;
import com.example.demo.repository.InsuranceRepository;
import com.example.demo.repository.MemberRepository;

@Controller
public class ClaimsController {

    @Autowired
    private ClaimRequestService cs;

    @Autowired
    private ClaimRequestRepository claimRequestRepository;
    
    @Autowired
    private InsuranceRepository insuranceRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    

    @PostMapping("/getMemberDetails")
    public String getMemberDetails(@RequestParam("memberId") String memberId, Model model) {
    	 try {
    	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	        String memberIdS = authentication.getName(); // Assuming username is memberId
    	        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

    	        model.addAttribute("memberId", memberIdS);
    	        model.addAttribute("role", role);
    		 memberId=memberId.toUpperCase();
             Member member = cs.getdata(memberId);
             //cs.getdata1(memberId);
             Insurance insurance = cs.getInsuranceByMemberId(memberId);
             
             model.addAttribute("member", member);
             model.addAttribute("insurance", insurance);
             model.addAttribute("claimRequest", new ClaimRequest());
             model.addAttribute("isError", false); // No error
           
             return "claimrequest2";

         } catch (RuntimeException e) {
        	    model.addAttribute("isError", true);
        	    model.addAttribute("errorMessage", e.getMessage()); // Add the error message
        	    return "claimrequest1"; // Return to the input page
        	}
    }    

    @PostMapping("/SubmitClaim")
    public String submitRequestClaim(@ModelAttribute ClaimRequest claimRequest) {

    	cs.saveData(claimRequest);
    	return "redirect:/claimrequest1";
    }

	/*
	 * @PostMapping("/getmemberdetails") public String
	 * getmemberdetails(@RequestParam("memberId") String memberId, Model model) {
	 * try { Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); String membername =
	 * authentication.getName(); // Assuming username is memberId String role =
	 * authentication.getAuthorities().iterator().next().getAuthority(); // Get the
	 * first role
	 * 
	 * 
	 * Member member = cs.getdata(memberId); cs.getdata1(memberId); Insurance
	 * insurance = cs.getInsuranceByMemberId(memberId);
	 * model.addAttribute("memberId", membername); model.addAttribute("role", role);
	 * model.addAttribute("member", member); model.addAttribute("insurance",
	 * insurance); model.addAttribute("claimRequest", new ClaimRequest());
	 * model.addAttribute("isError", false); // No error
	 * 
	 * return "userclaimrequest2";
	 * 
	 * } catch (RuntimeException e) { model.addAttribute("isError", true);
	 * model.addAttribute("errorMessage", e.getMessage()); // Add the error message
	 * return "userclaimrequest1"; // Return to the input page } }
	 * 
	 * @PostMapping("/submitClaim") public String
	 * submitUserRequestClaim(@ModelAttribute ClaimRequest claimRequest) {
	 * 
	 * cs.saveData(claimRequest); return "userclaimrequest1"; }
	 */
}
