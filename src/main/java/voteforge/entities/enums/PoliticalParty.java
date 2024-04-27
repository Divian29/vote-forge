package voteforge.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PoliticalParty {
    APP("Chidiogo"),

    PDA("Ikejiuba"),
    CCC("Olivia");

    private final String name;



}
