package com.keerthana.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keerthana.demo.model.Hr;
import com.keerthana.demo.serviceimpl.EmailServiceimpl;

@RestController
@RequestMapping("/hr")
@CrossOrigin("*")
public class HrController {

    @Autowired
    private EmailServiceimpl emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody Hr emailRequest) {
        try {
            emailService.sendSimpleMessage(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }
}
