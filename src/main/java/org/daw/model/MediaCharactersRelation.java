package org.daw.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MediaCharactersRelation {
    private String role;
    private Anime anime;
    private Manga manga;
}
