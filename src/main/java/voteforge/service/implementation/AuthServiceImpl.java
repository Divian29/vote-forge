package voteforge.service.implementation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import voteforge.entities.model.VoterEntity;
import voteforge.infrastucture.events.publisher.EventPublisher;
import voteforge.infrastucture.exceptions.ApplicationException;
import voteforge.payload.request.authRequest.ForgotPasswordResetRequest;
import voteforge.payload.request.authRequest.VoterSignUpRequest;
import voteforge.payload.response.ApiResponse;
import voteforge.payload.response.authResponse.VoterSignUpResponse;
import voteforge.repositories.VoterEntityRepository;
import voteforge.service.AuthService;

//import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final VoterEntityRepository voterEntityRepository;
    private final EventPublisher publisher;
    private final HttpServletRequest request;
//    private final JWTGenerator jwtGenerator;

    @Override
    public ResponseEntity<ApiResponse<VoterSignUpResponse>> registerVoter(VoterSignUpRequest voterSignUpRequest) {
        // Checks if a user's email is already in the database
        boolean isPresent = voterEntityRepository.existsByEmail(voterSignUpRequest.getEmail());

        // Throws and error if the email already exists
        if (isPresent) {
            throw new ApplicationException("Voter with this e-mail already exist");
        }

        // Maps the UserSignUpRequest dto to a User entity, so it can be saved
        VoterEntity newVoter = modelMapper.map(voterSignUpRequest, VoterEntity.class);

        // Save the user to the database
        VoterEntity savedVoter = voterEntityRepository.save(newVoter);

        // Publish and event to verify Email
        publisher.completeRegistrationEventPublisher(savedVoter.getEmail(), savedVoter.getName(), request);

        VoterSignUpResponse signupResponse = modelMapper.map(savedVoter, VoterSignUpResponse.class);

        // Return a ResponseEntity of a success message
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>("Account created successfully", signupResponse)
        );
    }

    @Override
    public ResponseEntity<ApiResponse<String>> forgotPassword(String email) {
        if (!voterEntityRepository.existsByEmail(email)) {
            throw new ApplicationException("Invalid email provided, please check and try again.");
        }

        publisher.forgotPasswordEventPublisher(email, request);

        return ResponseEntity.ok(new ApiResponse<>("A link has been sent to your email to reset your password"));
    }

    @Override
    public ResponseEntity<ApiResponse<String>> resetForgotPassword(ForgotPasswordResetRequest forgotPasswordResetRequest) {
//        if (!jwtGenerator.validateToken(forgotPasswordResetRequest.getToken())) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(new ApiResponse<>("Token expired, please request for a new one"));
//        }
//
//        String email = jwtGenerator.getEmailFromJWT(forgotPasswordResetRequest.getToken());
//
//        Optional<VoterEntity> voterOptional = voterEntityRepository.findByEmail(email);
//
//        if (voterOptional.isPresent()) {
//            VoterEntity user = voterOptional.get();
//
//            user.setPassword(passwordEncoder.encode(forgotPasswordResetRequest.getNewPassword()));
//
//            voterEntityRepository.save(user);
//
//            return ResponseEntity
//                    .status(HttpStatus.ACCEPTED)
//                    .body(new ApiResponse<>("Password Changed Successfully"));
//        }

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>("Invalid"));
    }
}
