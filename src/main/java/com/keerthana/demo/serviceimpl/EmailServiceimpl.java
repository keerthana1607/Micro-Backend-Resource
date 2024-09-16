package com.keerthana.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.keerthana.demo.service.EmailService;

@Service
public class EmailServiceimpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	public void sendMail(String recipientEmail, String recipientName) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(recipientEmail);
			message.setSubject("Project Assignment");
			message.setText("Hello " + recipientName + ",\n\nYou have been assigned to a new project.");

			emailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

}
