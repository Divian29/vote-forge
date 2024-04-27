package voteforge.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum ElectionCandidate {

    CANDIDATE_1(PoliticalParty.APP,"Chidiogo",Position.GOVERNORSHIP_POSITION),
    CANDIDATE_2(PoliticalParty.PDA,"Ikejiuba",Position.GOVERNORSHIP_POSITION),
    CANDIDATE_3(PoliticalParty.CCC,"Olivia",Position.GOVERNORSHIP_POSITION);

    private final PoliticalParty politicalParty;
    private final String name;
    private final Position position;



}
