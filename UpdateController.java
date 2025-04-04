package com.example.demo.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Insurance;
import com.example.demo.entity.Member;
import com.example.demo.service.UpdateService;

@Controller
public class UpdateController {

    @Autowired
    private UpdateService memberService;
    

    // Display input page
    @GetMapping("/member-input")
    public String showInputPage() {
        return "updateinput"; // Return the input view
    }

    // Fetch member details by ID
    @GetMapping("/member-details")
    public String getMemberDetails(@RequestParam("id") String id, Model model) {
        try {
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String memberId = authentication.getName(); // Assuming username is memberId
            String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role
            id=id.toUpperCase();
            model.addAttribute("memberId", memberId);
            model.addAttribute("role", role);
            Member member = memberService.getMemberById(id);
            model.addAttribute("member", member);
            model.addAttribute("isError", false); // No error
            return "updateoutput"; // Success view
        } catch (RuntimeException e) {
            // Catch the exception and set error message
            model.addAttribute("isError", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "updateinput"; // Error view
        }
    }

    // Update member details
    @PutMapping("/update-member")
    public String updateMemberDetails(
    		
            @RequestParam String member_id,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String dob,
            @RequestParam String address,
            @RequestParam String contact,
            @RequestParam String email,
            @RequestParam String gender,
            @RequestParam int nominee_count,
            @RequestParam String insurance_type,
            @RequestParam double insured_amount,
            @RequestParam double max_claimable_amount,
            Model model) {
    	
   	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memId = authentication.getName(); // Assuming username is memberId
        
        String message = "";
        try {
            // Fetch existing member
            Member member = memberService.getMemberById(member_id);
            member.setFirstName(first_name);
            member.setLastName(last_name);
            member.setDob(LocalDate.parse(dob));
            member.setAddress(address);
            member.setContactNo(contact);
            member.setEmail(email);
            member.setGender(gender);
            member.setNomineeCount(nominee_count);
            member.setLastUpdatedBy(memId);
            // Update insurance details if present
            if (!member.getInsurances().isEmpty()) {
                Insurance insurance = member.getInsurances().get(0);
                insurance.setInsuranceType(insurance_type);
                insurance.setInsuredAmount(insured_amount);
                insurance.setClaimableAmount(max_claimable_amount);
                insurance.setLastUpdatedBy(memId);
            }
           
            
            memberService.saveMember(member);
            message = "Member updated successfully";
        } catch (RuntimeException e) {
            message = e.getMessage(); // Set error message if exception occurs
        }
        model.addAttribute("message", message);
        return "redirect:/updateinput";
    }
}
