package voteforge.infrastucture.events.publisher;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import voteforge.infrastucture.events.event.CompleteRegistrationEvent;
import voteforge.infrastucture.events.event.ForgotPasswordEvent;
import voteforge.infrastucture.events.event.NotificationMailEvent;
import voteforge.utils.AuthenticationUtils;

@Component
@RequiredArgsConstructor
public class EventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void completeRegistrationEventPublisher(String email, String firstName, HttpServletRequest request) {
        applicationEventPublisher.publishEvent(new CompleteRegistrationEvent(email, firstName, AuthenticationUtils.applicationUrl(request)));
    }

    public void forgotPasswordEventPublisher(String email, HttpServletRequest request) {
        applicationEventPublisher.publishEvent(new ForgotPasswordEvent(email, AuthenticationUtils.applicationUrl(request)));
    }

    public void notificationMailEventPublisher(String email, String firstName, String subject, String description, HttpServletRequest request) {
        applicationEventPublisher.publishEvent(new NotificationMailEvent(email, firstName, subject, description, AuthenticationUtils.applicationUrl(request)));
    }
}
