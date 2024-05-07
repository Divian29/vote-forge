package voteforge.service;

public interface EmailSenderService {
    void sendRegistrationEmailVerification(String url, String email, String firstName);
}
