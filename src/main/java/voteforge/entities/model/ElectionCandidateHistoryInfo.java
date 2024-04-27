package voteforge.entities.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voting_candidate")
public class ElectionCandidateHistoryInfo extends BaseEntity{
    private String candidatesBiography;

    private int age;

    private String candidateProfilePicture;



}
