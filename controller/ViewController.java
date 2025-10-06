package com.example.demo.controller;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	
	
	/*
	 * @GetMapping("/dashboard") public String dashboard(Model model, HttpSession
	 * session) { String userRole = (String) session.getAttribute("userRole");
	 * model.addAttribute("userRole", userRole); return "dashboard"; }
	 */
	 
	@GetMapping("/")
    public String redirectToRoleBasedPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "redirect:/adminhome";
        } else {
            return "redirect:/userhome";
        }
    }
	
	@GetMapping("/userhome")
    public String userMain(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberId", memberId);
        model.addAttribute("role", role);

        return "userhome";
	}
	
	@GetMapping("/adminhome")
    public String adminMain(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberId", memberId);
        model.addAttribute("role", role);

        return "adminhome";
	}
	
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberId", memberId);
        model.addAttribute("role", role);
        return "register";
    }		
    
    @GetMapping("/claimrequest1")
    public String showClaimPage(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberId", memberId);
        model.addAttribute("role", role);
        return "claimrequest1";
    }
    
    
    @GetMapping("/claimrequest2")
    public String showClaimPage2(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberId", memberId);
        model.addAttribute("role", role);
        return "claimrequest2";
    }

    @GetMapping("/usersearchclaim")
    public String showUserSearchClaim(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberId", memberId);
        model.addAttribute("role", role); // Ensure error attribute is null by default
        return "usersearchclaim"; // Display the input.html page
    }

    @GetMapping("/searchclaim")
    public String showSearchClaim(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberId", memberId);
        model.addAttribute("role", role);// Ensure error attribute is null by default
        return "searchclaim"; // Display the input.html page
    }
    
    @GetMapping("/updateinput")
    public String showUpdate(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberId", memberId);
        model.addAttribute("role", role);
        return "updateinput";
    }
    @GetMapping("/updateoutput")
    public String showUpdateOutput(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberId", memberId);
        model.addAttribute("role", role);
        return "updateoutput";
    }
	/*
	 * @GetMapping("/userclaimrequest1") public String showUserClaim() { return
	 * "userclaimrequest1"; }
	 * 
	 * @GetMapping("/userclaimrequest2") public String showUserClaim2() { return
	 * "userclaimrequest2"; }
	 */
    
    @GetMapping("/processsearch")
    public String searchPage(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberIds = authentication.getName(); // Assuming username is memberId
        String role = authentication.getAuthorities().iterator().next().getAuthority(); // Get the first role

        model.addAttribute("memberIds", memberIds);
        model.addAttribute("role", role);
        return "processsearch";
    }
}