package voteforge.entities.model;

import jakarta.persistence.*;
import lombok.*;
import voteforge.entities.enums.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "election_table")
public class Election extends BaseEntity{
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Position positionOfElection;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

     @Enumerated(EnumType.STRING)
    private ElectionCandidate electionCandidates;

    @Enumerated(EnumType.STRING)
    private VotersStatus votersStatus;

    @Enumerated(EnumType.STRING)
    private ElectionStatus electionStatus;

    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<ElectionResult> results;

    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<VoterEntity> voterEntity;

    @OneToOne(mappedBy = "election")
    private VotersHistory votersHistory;




}
