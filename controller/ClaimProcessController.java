package com.example.demo.controller;

import com.example.demo.entity.ClaimRequest;
import com.example.demo.entity.Member;
import com.example.demo.service.ClaimProcessService;
import com.example.demo.service.MemberProcessService;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class ClaimProcessController {

    @Autowired
    private ClaimProcessService claimProcessService;

    @Autowired
    private MemberProcessService memberService;

    @GetMapping("/searchMember")
    public String searchMember(@RequestParam String memberId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberIds = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        model.addAttribute("memberIds", memberIds);
        model.addAttribute("role", role);
        List<ClaimRequest> claimRequests = memberService.getClaimRequestsByMemberId(memberId);
        model.addAttribute("claimRequests", claimRequests);
        model.addAttribute("memberId", memberId);
        return "processsearch";
    }

    @GetMapping("/claimDetails/{requestId}")
    public String getClaimDetails(@PathVariable String requestId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberIds = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberIds", memberIds);
        model.addAttribute("role", role);
        ClaimRequest claimRequest = claimProcessService.getClaimRequestById(requestId);
        if (claimRequest == null) {
            System.err.println("ClaimRequest not found for requestId: " + requestId);
            return "redirect:/processsearch?error=claimNotFound";
        }
        Member member = claimRequest.getMember();
        if (member == null) {
            System.err.println("Member not found for claimRequest: " + requestId);
            return "redirect:/processsearch?error=memberNotFound";
        }
        model.addAttribute("claimRequest", claimRequest);
        model.addAttribute("member", member);
        System.err.println("sent the response");
        return "claimprocess";
    }

    @PostMapping("/approveClaim/{requestId}")
    public String approveClaim(@PathVariable String requestId,
                             @RequestParam("document1") MultipartFile document1,
                             @RequestParam("document2") MultipartFile document2,
                             @RequestParam("document3") MultipartFile document3) {
        try {
            System.out.println("Approving claim for requestId: " + requestId);
            String uploadDir = "uploads/";
            String document1Path = uploadDir + document1.getOriginalFilename();
            String document2Path = uploadDir + document2.getOriginalFilename();
            String document3Path = uploadDir + document3.getOriginalFilename();

            saveFile(uploadDir, document1.getOriginalFilename(), document1);
            saveFile(uploadDir, document2.getOriginalFilename(), document2);
            saveFile(uploadDir, document3.getOriginalFilename(), document3);

            claimProcessService.approveClaim(requestId, document1Path, document2Path, document3Path);

            System.out.println("Claim approved successfully.");
            return "redirect:/processsearch";
        } catch (IOException e) {
            System.err.println("Error uploading files: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/processsearch?error=approveError";
        } catch (Exception e) {
            System.err.println("Error approving claim: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/processsearch?error=approveError";
        }
    }

    private void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        try (var inputStream = multipartFile.getInputStream()){
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

    }

    @PostMapping("/rejectClaim/{requestId}")
    public String rejectClaim(@PathVariable String requestId,
                            @RequestParam String rejectionReason) {
        claimProcessService.rejectClaim(requestId, rejectionReason);
        return "redirect:/processsearch";
    }
}