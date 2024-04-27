package voteforge.entities.model;

import jakarta.persistence.*;
import lombok.*;
import voteforge.entities.enums.ElectionCandidate;
import voteforge.entities.enums.PoliticalParty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "election_result")
public class ElectionResult extends BaseEntity{

    private Long numberOfVotesByEachCandidates;

    @Enumerated(EnumType.STRING)
    private ElectionCandidate winningCandidate;

    @Enumerated(EnumType.STRING)
    private PoliticalParty politicalParty;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Election election;




}
