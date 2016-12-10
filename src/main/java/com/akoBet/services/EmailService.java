package com.akoBet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Arek on 10.12.2016.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, String title, String contents) {

        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("info.akoBet@gmail.com");
            helper.setFrom("info@akoBet.pl");
            helper.setSubject(title);
            helper.setText(contents);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
        }

        javaMailSender.send(mail);

    }
}
