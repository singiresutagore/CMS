package com.example.demo.service;

import com.example.demo.controller.MemberRegistrationRequest;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.regex.Pattern;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String MEMBER_ID_PREFIX = "MBC-";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    @Transactional
    public Member registerMember(MemberRegistrationRequest request) throws MemberValidationException {
        Member member = new Member();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memId = authentication.getName(); // Assuming username is memberId
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setDob(request.getDob());
        member.setAddress(request.getAddress());
        member.setContactNo(request.getContactNo());
        member.setEmail(request.getEmail());
        member.setGender(request.getGender());
        member.setNomineeCount(request.getNomineeCount());
        member.setCreatedBy(memId);
        member.setLastUpdatedBy(memId);
        member.setRole("USER"); // Default role
        validateMember(member);
        member.setMemberId(generateMemberId());
        member.setCreatedDate(LocalDate.now());
        member.setLastUpdatedDate(LocalDate.now());
        member.setCitizenType(determineCitizenType(member.getDob()));
        String rawPassword = generatePassword(member.getDob(), member.getMemberId());
        member.setPassword(passwordEncoder.encode(rawPassword));
        return memberRepository.save(member);
    }

    private void validateMember(Member member) throws MemberValidationException {
        if (!member.getFirstName().matches("[a-zA-Z]+") || !member.getLastName().matches("[a-zA-Z]+")) {
            throw new MemberValidationException("Member name should contain only alphabets.");
        }
        if (!EMAIL_PATTERN.matcher(member.getEmail()).matches()) {
            throw new MemberValidationException("Invalid email format.");
        }
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            throw new MemberValidationException("Email already exists in the system.");
        }
        if (!member.getContactNo().matches("\\d+")) {
            throw new MemberValidationException("Contact number should contain only digits.");
        }
        if (member.getDob().isAfter(LocalDate.now())) {
            throw new MemberValidationException("Date of birth cannot be after the current date.");
        }
        if (member.getNomineeCount() < 1 || member.getNomineeCount() > 3) {
            throw new MemberValidationException("Nominee count should be between 1 and 3.");
        }
        if (member.getGender() == null || member.getGender().isEmpty()) {
            throw new MemberValidationException("Gender needs to be selected.");
        }
    }

    private String generatePassword(LocalDate dob, String memberId) {
        return dob.format(java.time.format.DateTimeFormatter.ofPattern("ddMM")) + memberId.substring(4);
    }

    private String generateMemberId() {
        long count = memberRepository.count();
        return MEMBER_ID_PREFIX + String.format("%05d", count + 1);
    }

    private String determineCitizenType(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears() >= 18 ? "Adult" : "Minor";
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found"));

        return new User(member.getMemberId(), member.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(member.getRole())));
    }
}

class MemberValidationException extends Exception {
    public MemberValidationException(String message) {
        super(message);
    }
    public MemberValidationException(){}
}