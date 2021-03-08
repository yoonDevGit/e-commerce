package com.ecommerce.rooms.service;

import com.ecommerce.rooms.dto.MailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(false)
class MailServiceTest {

  @MockBean
  private JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String to;

  @Test
  public void sendMail() throws Exception {
      // GIVEN
    MailDto mailDto = new MailDto("yoondev93@gmail.com", "TestMessage", "Success");

      // WHEN
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setFrom(mailDto.getFrom());
    message.setSubject(mailDto.getTitle());
    message.setText(mailDto.getMessage());

    mailSender.send(message);
  }
}