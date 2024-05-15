package voteforge.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import voteforge.entities.model.VoterEntity;
import voteforge.infrastucture.events.event.ForgotPasswordEvent;
import voteforge.repositories.VoterEntityRepository;
import voteforge.service.EmailSenderService;
import voteforge.utils.HelperClass;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;
    private final HelperClass helperClass;

    @Value("${spring.mail.username}")
    private String senderMail;
    private final VoterEntityRepository voterEntityRepository;

    @Override
    public void sendNotificationEmail(String url,
                                      String email,
                                      String firstName,
                                      String subject,
                                      String description) {

        String action = "Contact Us";
        String serviceProvider = "BooksVille Customer Service";

        helperClass.sendNotificationEmail(
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

    @Override
    public void sendForgotPasswordEmailVerification(String url, ForgotPasswordEvent event) {
        Optional<VoterEntity> optionalVoter = voterEntityRepository.findByEmail(event.getEmail());


        String action = "Change Password";
        String serviceProvider = "BooksVille Registration Portal Service";
        String subject = "Email Verification";
        String description = "Please follow the link below to change your password.";

        if (optionalVoter.isPresent()) {
            VoterEntity voter = optionalVoter.get();


            helperClass.sendEmail(
                    voter.getName(),
                    url,
                    mailSender,
                    senderMail,
                    event.getEmail(),
                    action,
                    serviceProvider,
                    subject,
                    description
            );
        }
    }
}
