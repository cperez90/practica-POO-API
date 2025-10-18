package org.daw.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CharacterItems {
    private Character character;
    private String role;
    private List<VoiceActor> voiceActors;
}
