package voteforge.infrastucture.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voteforge.payload.request.authRequest.ForgotPasswordRequest;
import voteforge.payload.request.authRequest.ForgotPasswordResetRequest;
import voteforge.payload.request.authRequest.VoterSignUpRequest;
import voteforge.payload.response.ApiResponse;
import voteforge.payload.response.authResponse.VoterSignUpResponse;
import voteforge.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register-voter")
    public ResponseEntity<ApiResponse<VoterSignUpResponse>> registerVoter(@Valid @RequestBody VoterSignUpRequest voterSignUpRequest) {
        return authService.registerVoter(voterSignUpRequest);
    }

    @PostMapping("/voter-forgot-password")
    public ResponseEntity<ApiResponse<String>> userForgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return authService.forgotPassword(forgotPasswordRequest.getEmail());
    }

    @PostMapping(value = "/voter-reset-forgot-password")
    public ResponseEntity<ApiResponse<String>> userResetForgotPassword(@Valid @RequestBody ForgotPasswordResetRequest forgotPasswordResetRequest) {
        return authService.resetForgotPassword(forgotPasswordResetRequest);
    }
}
