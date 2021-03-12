package com.ecommerce.rooms.service;

import com.ecommerce.rooms.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String from;

  public void sendMail(MailDto mailDto) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(from);
      message.setTo(mailDto.getTo());
      message.setSubject(mailDto.getTitle());
      message.setText(mailDto.getMessage());

      mailSender.send(message);
    } catch (Exception e) {
      System.out.println("e.getMessage() = " + e.getMessage());
    }
  }


}
