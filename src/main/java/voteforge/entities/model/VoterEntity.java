package voteforge.entities.model;

import jakarta.persistence.*;
import lombok.*;
import voteforge.entities.enums.ElectionCandidate;
import voteforge.entities.enums.Roles;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voter_entity")
public class VoterEntity extends BaseEntity{

    private  String name;

    private int age;

    private String address;

    private String email;

    private String password;

    @Transient
    private String confirmPassword;

    private String profilePicture;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Election election;

    @Enumerated(EnumType.STRING)
    private ElectionCandidate electionCandidates;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    private boolean isVerified;

    @OneToOne(mappedBy = "voterEntity")
    private VotersHistory votersHistory;



}
