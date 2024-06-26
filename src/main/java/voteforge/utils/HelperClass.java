package voteforge.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import voteforge.entities.model.VoterEntity;
import voteforge.infrastucture.exceptions.ApplicationException;
import voteforge.repositories.VoterEntityRepository;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelperClass {
    private final JavaMailSender javaMailSender;
    private final VoterEntityRepository voterEntityRepository;

    public void sendEmail(
            String name,
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
                    + "<p> Hi, " + name + " </p>"
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

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Async
    public void sendNotificationEmail(
            String name,
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
            String mailContent ="<div style='padding: 1rem; background-color: #A6F4C5; color: white'>"
                    + "<p style='text-align: center'>"
                    + "<img src=" + AppConstants.LOGO + " style='width: 8rem'>"
                    + "<p style='font-family: Academy Engraved LET; font-size: 20px; text-align: center'> VOTEFORGE </p>"
                    + "</p>"
                    + "<hr style='color: black'>"
                    + "<p> Hi, " + name + " </p>"
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

    public String emailVerification(
            String email,
            String action,
            String buttonName,
            String serviceProvider,
            String description
    ) {

        String name = extractName(email);

        return "<head>"
                + "<title> " + action + " </title> "
                + "</head>"
                + "<body style='height: 100vh; overflow: hidden; margin: 0'>"
                + "<div style='padding: 2rem; background-color: #A6F4C5; color: white; height: 100vh; width: 100vw; font-size: 20px; display: flex; justify-content: center; overflow: hidden'>"
                + "<div style='width: 35vw; color: black'>"
                + "<p style='text-align: center'>"
                + "<img src=" + AppConstants.LOGO + " style='width: 8rem'>"
                + "<p style='font-family: Academy Engraved LET; font-size: 20px; text-align: center'> VOTEFORGE </p>"
                + "</p>"
                + "<hr style='color: black'>"
                + "<p style='font-family: Academy Engraved LET; font-size: 30px'> Hi, " + name + " </p>"
                + "<p style='font-family: Cochin; margin-bottom: 1.5rem'> " + description + " </p>"
                + "<a href='http://127.0.0.1:2024/login' style='font-family: Cochin; width: 35vw; height: 4rem; border-radius: 1rem; border: 1px solid #27AE60; margin-top: 1rem; margin-bottom: 4rem; opacity: 0.8; background-color: #27AE60; color: white; font-size: 18px; cursor: pointer; padding: 1rem 2rem; text-align: center; text-decoration: none' />"
                + buttonName
                + "</a>"
                + "<p style='font-family: Cochin; margin-top: 1.5rem'> &copy; &nbsp;" + serviceProvider + " </p>"
                + "</div>"
                + "</div>"
                + "</body>";
    }

    public String extractName(String email) {
        Optional<VoterEntity> voterEntityOptional = voterEntityRepository.findByEmail(email);

        if (voterEntityOptional.isPresent()) {
            return voterEntityOptional.get().getName();
        }

        return "";
    }
}
