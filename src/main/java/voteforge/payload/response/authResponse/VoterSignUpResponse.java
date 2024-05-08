package voteforge.payload.response.authResponse;

import lombok.*;
import voteforge.entities.enums.Gender;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoterSignUpResponse {
    private Long id;

    private String name;

    private String email;

    private Gender gender;
}
