package voteforge.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import voteforge.service.EmailSenderService;
import voteforge.utils.HelperClass;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;
    private final HelperClass helperClass;

    @Value("${spring.mail.username}")
    private String senderMail;

    @Override
    public void sendRegistrationEmailVerification(String url, String email, String firstName) {
        String action = "Verify Email";
        String serviceProvider = "VoteForge Registration Portal Service";
        String subject = "Email Verification";
        String description = "Please follow the link below to complete your registration.";

        helperClass.sendEmail(
                firstName,
                url,
                mailSender,
                senderMail,
                email,
                action,
                serviceProvider,
                subject,
                description
        );
    }
}
