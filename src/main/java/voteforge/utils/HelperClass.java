package voteforge.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import voteforge.infrastucture.exceptions.ApplicationException;

import java.io.UnsupportedEncodingException;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelperClass {
    private final JavaMailSender javaMailSender;

    public void sendEmail(
            String firstName,
            String url,
            JavaMailSender mailSender,
            String sendMail,
            String recipient,
            String action,
            String serviceProvider,
            String subject,
            String description
    ) {

        try {
            String mailContent ="<div style='padding: 1rem; background-color: white; color: black'>"
                    + "<p style='text-align: center'>"
                    + "<img src=" + AppConstants.LOGO + " style='width: 8rem'>"
                    + "<p style='font-family: Academy Engraved LET; font-size: 20px; text-align: center'> VOTEFORGE </p>"
                    + "</p>"
                    + "<hr style='color: black'>"
                    + "<p> Hi, " + firstName + "</p>"
                    + "<p> " + description + " </p>"
                    + "<a href=" + url + " style='padding: 0.7rem; background-color: #27AE60; text-decoration: none; border-radius: 0.3rem; color: white'>" + action + "</a> <br>"
                    + "<p> Thank you. <br> " + serviceProvider + " </p>"
                    + "</div>";

            MimeMessage message = mailSender.createMimeMessage();

            var messageHelper = new MimeMessageHelper(message);

            messageHelper.setFrom(sendMail, serviceProvider);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(mailContent, true);

            mailSender.send(message);

        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            throw new ApplicationException(e.getMessage());
        }
    }
}
