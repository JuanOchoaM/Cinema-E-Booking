package com.spring.project.controllers;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.project.users.*;
import com.spring.project.services.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Controller
public class registrationController {

    @Autowired
    private UserRepository userRepo; // Reference to user repository interface

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/confirm")
    public String confirm() { 
        return "confirmation";
    } // confirm

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    } // signup

    @PostMapping("/process_register")
    public String processRegister(User user) throws MessagingException, UnsupportedEncodingException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // checking if email exists
        // TODO: Have reg page say duplicate email if duplicate
       User existingAccount = userRepo.findByEmail(user.getEmail());
        if (existingAccount != null)
        {
            return "signup";
        }

        generateCode(user);
        sendVerEmail(user);

        userRepo.save(user);
    
        return "register_success";
    }

    @GetMapping("verification")
    public String verification() {
        return "verification";
    }

    @PostMapping("/verifyUser")
    public String verifyUser(@RequestParam("code") String code) {
        if (code == "VERIFIED") {
            return verification();
        }

        User user = userRepo.findByVerCode(code);

        if (user == null) {
            // TODO: make it so verification page says that the code was wrong
            return "verification";
        }

        user.setVerCode("VERIFIED");

        return "verify_success";
    }

    public void generateCode(User user) {
        Random rand = new Random();
        int code = rand.nextInt(900000) + 100000;
        user.setVerCode(String.valueOf(code));
    }

    public void sendVerEmail(User user) throws MessagingException, UnsupportedEncodingException {
        // TODO: make email look nicer and use actual html

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("tidalwavetheaters@gmail.com", "Tidal Wave Theaters");
        helper.setTo(user.getEmail());
        helper.setSubject("Verify Your Account");
        helper.setText(user.getVerCode() + "\nlocalhost:8080/verification");

        mailSender.send(message);

    }

}
