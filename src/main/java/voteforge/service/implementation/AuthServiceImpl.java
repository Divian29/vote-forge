package voteforge.service.implementation;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import voteforge.entities.model.VoterEntity;
import voteforge.infrastucture.exceptions.ApplicationException;
import voteforge.payload.request.authRequest.VoterSignUpRequest;
import voteforge.payload.response.ApiResponse;
import voteforge.payload.response.authResponse.VoterSignUpResponse;
import voteforge.repositories.VoterEntityRepository;
import voteforge.service.AuthService;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final VoterEntityRepository voterEntityRepository;

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

        VoterSignUpResponse signupResponse = modelMapper.map(savedVoter, VoterSignUpResponse.class);

        // Return a ResponseEntity of a success message
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>("Account created successfully", signupResponse)
        );
    }
}
