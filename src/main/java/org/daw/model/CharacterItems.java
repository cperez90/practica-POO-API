package org.daw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterItems {
    private Character character;
    private String role;
    private List<VoiceActor> voiceActors;
}
