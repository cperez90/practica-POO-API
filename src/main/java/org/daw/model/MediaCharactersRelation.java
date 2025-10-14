package org.daw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaCharactersRelation {
    private String role;
    private Anime anime;
    private Manga manga;
}
