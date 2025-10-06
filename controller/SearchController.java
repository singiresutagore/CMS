package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ClaimRequest;
import com.example.demo.entity.Insurance;
import com.example.demo.entity.Member;
import com.example.demo.service.SearchService;

@Controller
public class SearchController {

    @Autowired
    private SearchService userService;
    
   @GetMapping("/userdata")
    public String getDataByMemberId(
            @RequestParam("memberId") String memberId,
            @RequestParam(value = "memberName", required = false) String memberName,
            @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            Model model) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String memberIds = authentication.getName(); // Assuming username is memberId
       String role = authentication.getAuthorities().iterator().next().getAuthority();
       model.addAttribute("memberId", memberIds);
       model.addAttribute("role", role);
        Member member = userService.getMemberById(memberId);
        if (member != null) {
            boolean isValidName = false;
            boolean isValidDate = false;

            // Ensure at least one input is provided
            if ((memberName == null || memberName.isEmpty()) && (fromDate == null || toDate == null)) {
                model.addAttribute("error", "Validation failed: Either member name or date range must be provided.");
                return "searchclaim";
            }

            // Validate member name
            if (memberName != null && !memberName.isEmpty()) {
                if (member.getFirstName().equalsIgnoreCase(memberName)) {
                    isValidName = true;
                } else {
                    model.addAttribute("error", "Validation failed: Member name does not match.");
                }
            }

            // Validate date range
            if (fromDate != null && toDate != null) {
                if (toDate.isAfter(LocalDate.now())) {
                    model.addAttribute("error", "Validation failed: To date cannot be greater than today.");
                } else {
                    List<ClaimRequest> claims = userService.getClaimsByMember(member);
                    for (ClaimRequest claim : claims) {
                        if (!claim.getRequestDate().isBefore(fromDate) && !claim.getRequestDate().isAfter(toDate)) {
                            isValidDate = true;
                            break;
                        }
                    }
                    if (!isValidDate) {
                        model.addAttribute("error", "No claims were made between these dates.");
                    }
                }
            }

            // Check if both validations are required and passed
            if ((memberName == null || memberName.isEmpty() || isValidName) && (fromDate == null || toDate == null || isValidDate)) {
                Optional<Insurance> insurance = userService.getInsurancesByMember(member);
                List<ClaimRequest> claims = userService.getClaimsByMember(member);
                model.addAttribute("member", member);
                model.addAttribute("claims", claims);
                model.addAttribute("insurance", insurance);
                return "searchclaim";
            }
        } else {
            model.addAttribute("error", "Member not found.");
        }
        return "searchclaim";
    }
   
   
   @GetMapping("/usersearchdata")
   public String getDataByMemberID(
           @RequestParam("memberId") String memberId,
           @RequestParam(value = "memberName", required = false) String memberName,
           @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
           @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
           Model model) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String memberIds = authentication.getName(); // Assuming username is memberId
       String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

       model.addAttribute("memberId", memberIds);
       model.addAttribute("role", role);

       Member member = userService.getMemberById(memberId);
       if (member != null) {
           boolean isValidName = false;
           boolean isValidDate = false;

           // Ensure at least one input is provided
           if ((memberName == null || memberName.isEmpty()) && (fromDate == null || toDate == null)) {
               model.addAttribute("error", "Validation failed: Either member name or date range must be provided.");
               return "usersearchclaim";
           }

           // Validate member name
           if (memberName != null && !memberName.isEmpty()) {
               if (member.getFirstName().equalsIgnoreCase(memberName)) {
                   isValidName = true;
               } else {
                   model.addAttribute("error", "Validation failed: Member name does not match.");
               }
           }

           // Validate date range
           if (fromDate != null && toDate != null) {
               if (toDate.isAfter(LocalDate.now())) {
                   model.addAttribute("error", "Validation failed: To date cannot be greater than today.");
               } else {
                   List<ClaimRequest> claims = userService.getClaimsByMember(member);
                   for (ClaimRequest claim : claims) {
                       if (!claim.getRequestDate().isBefore(fromDate) && !claim.getRequestDate().isAfter(toDate)) {
                           isValidDate = true;
                           break;
                       }
                   }
                   if (!isValidDate) {
                       model.addAttribute("error", "No claims were made between these dates.");
                   }
               }
           }

           // Check if both validations are required and passed
           if ((memberName == null || memberName.isEmpty() || isValidName) && (fromDate == null || toDate == null || isValidDate)) {
               Optional<Insurance> insurance = userService.getInsurancesByMember(member);
               List<ClaimRequest> claims = userService.getClaimsByMember(member);
               model.addAttribute("member", member);
               model.addAttribute("claims", claims);
               model.addAttribute("insurance", insurance);
               return "usersearchclaim";
           }
       } else {
           model.addAttribute("error", "Member not found.");
       }
       return "usersearchclaim";
   }

}
