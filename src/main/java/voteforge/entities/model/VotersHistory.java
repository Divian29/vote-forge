package voteforge.entities.model;

import jakarta.persistence.*;
import lombok.*;
import voteforge.entities.enums.ElectionCandidate;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VotersHistory extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "voter_id")
    private VoterEntity voterEntity;

    @OneToOne
    @JoinColumn(name = "election_id")
    private Election election;

    @Enumerated(EnumType.STRING)
    private ElectionCandidate electionCandidate;

    private LocalDateTime timestamp;




}
