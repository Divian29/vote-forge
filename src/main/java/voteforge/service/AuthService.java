package voteforge.service;

import org.springframework.http.ResponseEntity;
import voteforge.payload.request.authRequest.VoterSignUpRequest;
import voteforge.payload.response.ApiResponse;
import voteforge.payload.response.authResponse.VoterSignUpResponse;

public interface AuthService {
    ResponseEntity<ApiResponse<VoterSignUpResponse>> registerVoter(VoterSignUpRequest voterSignUpRequest);
}
